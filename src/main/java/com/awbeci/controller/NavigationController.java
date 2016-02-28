package com.awbeci.controller;

import com.awbeci.domain.User;
import com.awbeci.domain.UserCategory;
import com.awbeci.domain.UserSites;
import com.awbeci.service.IUserCategoryService;
import com.awbeci.service.IUserSitesService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Collector;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.awbeci.aliyun.oss.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class NavigationController {

    Logger log = LoggerFactory.getLogger(NavigationController.class);

    @Autowired
    private IUserCategoryService userCategoryService;

    @Autowired
    private IUserSitesService userSitesService;

    @RequestMapping(value = "/json/getCategoryByUid.json", method = RequestMethod.POST)
    @ResponseBody
    public List<UserCategory> selectAllCategory(HttpSession session) {
        String uid = (String) session.getAttribute("current_navigation_id");
        if (uid != null) {
            List<UserCategory> userCategories = userCategoryService.selectCategoryByUid(uid);
            return userCategories;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/json/getCategoryParent.json", method = RequestMethod.POST)
    @ResponseBody
    public List<UserCategory> selectCategoryParent(HttpSession session) {
        String uid = (String) session.getAttribute("current_navigation_id");
        if (uid != null) {
            List<UserCategory> userCategories = userCategoryService.selectCategoryParent(uid);
            return userCategories;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/json/getCategoryChildByPid.json", method = RequestMethod.POST)
    @ResponseBody
    public List<UserCategory> getCategoryChildByPid(String pid, HttpSession session) {
        String uid = (String) session.getAttribute("current_navigation_id");
        if (uid != null) {
            List<UserCategory> userCategories = userCategoryService.selectCategoryChildByPid(pid);
            return userCategories;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/json/getCategoryChild.json", method = RequestMethod.POST)
    @ResponseBody
    public Map getCategoryChild(HttpSession session) {
        String uid = (String) session.getAttribute("current_navigation_id");
        Map map = new HashedMap();
        if (uid != null) {
            List<UserCategory> userCategories = userCategoryService.selectCategoryChild(uid);
            List<UserCategory> childs = userCategories.stream().filter(pid -> (pid.getPid() != null || !pid.getPid().trim().equals(""))).collect(Collectors.toList());
            List<UserCategory> parents = userCategories.stream().filter(pid -> (pid.getPid() == null || pid.getPid().trim().equals(""))).collect(Collectors.toList());
            map.put("childs",childs);
            map.put("parents",parents);
            return map;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/json/saveCategory.json", method = RequestMethod.POST)
    @ResponseBody
    public int saveCategory(UserCategory userCategory, HttpServletRequest request, HttpSession session) {
        String flag = request.getParameter("flag");
        String uid = (String) session.getAttribute("uid");
        if (uid != null) {

            userCategory.setUpdateDt(new Date());

            if (flag.equals("add")) {
                userCategory.setId(UUID.randomUUID().toString());
                userCategory.setUid(uid);
                userCategory.setSortNo(1);
                userCategory.setCreateDt(new Date());
                int val = userCategoryService.insertCategory(userCategory);
                return val;
            }
            if (flag.equals("update")) {
                int val = userCategoryService.updateCategoryById(userCategory);
                return val;
            }
            return 0;
        } else {
            return 0;
        }
    }

    @RequestMapping(value = "/json/saveSite.json", method = RequestMethod.POST)
    @ResponseBody
    public int saveSite(UserSites userSites, HttpServletRequest request, HttpSession session) throws IOException {
        String flag = request.getParameter("flag");
        String uid = (String) session.getAttribute("uid");
        userSites.setuId(uid);
        String properties = "aliyun-oss.properties";
        if (uid != null) {
            return userSitesService.saveSite(flag, properties, userSites);
        } else {
            return 0;
        }
    }

    @RequestMapping(value = "/json/uploadAvatar.json", method = RequestMethod.POST)
    @ResponseBody
    public String uploadAvatar(@RequestParam("croppedImage") MultipartFile file, @RequestParam("avatarUrl") String avatarImg, HttpSession session) {
        try {
            String uid = (String) session.getAttribute("uid");
            InputStream content = file.getInputStream();
            String properties = "aliyun-oss.properties";
            String filepath = userSitesService.uploadAvatar(properties, content, uid, avatarImg);
            User user = (User) session.getAttribute("user");
            user.setAvatarUrl(filepath);
            session.setAttribute("user", user);
            return filepath;
        } catch (Exception ex) {
            log.error("上传头像失败：" + ex.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/json/deleteSite.json", method = RequestMethod.POST)
    @ResponseBody
    public int deleteSite(String id, String iconurl, HttpSession session) {
        String uid = (String) session.getAttribute("uid");
        if (uid != null) {
            String properties = "aliyun-oss.properties";
            int val = userSitesService.deleteSite(properties, id, iconurl);
            return val;
        } else {
            return 0;
        }
    }

    @RequestMapping(value = "/json/deleteCategory.json", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteCategory(String id, HttpSession session) {
        String uid = (String) session.getAttribute("uid");
        Map<String, Object> map = new HashMap<String, Object>();
        if (uid != null) {
            List userSites = userSitesService.getUserSitesByCategoryId(id);
            if (userSites.size() > 0) {
                map.put("success", false);
                map.put("msg", "该分类下存在网址，请先删除网址再删除该分类");
            } else {
                int val = userCategoryService.deleteCategory(id);
                map.put("success", true);
                map.put("msg", "删除成功");
            }
        } else {
            map.put("success", false);
            map.put("msg", "请先登录");
        }
        return map;
    }

    @RequestMapping(value = "/json/getSiteByCategoryId.json", method = RequestMethod.POST)
    @ResponseBody
    public List<UserSites> getSiteByCategoryId(String categoryId, HttpSession session) {
        String uid = (String) session.getAttribute("current_navigation_id");
        if (uid != null) {
            List userSites = userSitesService.getSiteByCategoryId(categoryId);
            return userSites;
        }
        return null;
    }

    @RequestMapping(value = "/json/getSiteByMostClick.json", method = RequestMethod.POST)
    @ResponseBody
    public List<UserSites> getSitesByMostClick(HttpSession session) {
        String uid = (String) session.getAttribute("current_navigation_id");
        if (uid != null) {
            List userSites = userSitesService.getSitesByMostClick(uid);
            return userSites;
        }
        return null;
    }

    @RequestMapping(value = "/json/querySiteByParam.json", method = RequestMethod.POST)
    @ResponseBody
    public List<UserSites> querySiteByParam(String param, HttpSession session) {
        try {
            String uid = (String) session.getAttribute("current_navigation_id");
            return userSitesService.querySiteByParam(param, uid);
        } catch (Exception e) {
            log.debug("错误原因:" + e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/json/querySiteByDomainName.json", method = RequestMethod.POST)
    @ResponseBody
    public List<UserSites> querySiteByDomainName(String param, HttpSession session) {
        try {
            String uid = (String) session.getAttribute("current_navigation_id");
            return userSitesService.querySiteByUrl(param, uid);
        } catch (Exception e) {
            log.debug("错误原因:" + e.getMessage());
            return null;
        }
    }
}