package com.awbeci.controller;

import com.awbeci.domain.SystemCode;
import com.awbeci.domain.User;
import com.awbeci.domain.UserCategory;
import com.awbeci.domain.UserFollow;
import com.awbeci.service.IUserCategoryService;
import com.awbeci.service.IUserFollowService;
import com.awbeci.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserCategoryService userCategoryService;

    @Autowired
    IUserFollowService userFollowService;

    /**
     * 网站主页
     */
    @RequestMapping("/")
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "main/index";
        } else {
            return "/main/main";
        }
    }

    /**
     * 导航到用户个人主页
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/{username}/navigation")
    public String usersPage(@PathVariable String username, HttpSession session, Model model) {
        User user = userService.selectUserByName(username);
        if (user == null) {
            return "error/404";
        } else {
            Object uidObj = session.getAttribute("uid");
            if (uidObj != null) {
                String uid = uidObj.toString();
                //判断是否等于当前用户
                model.addAttribute("isme", user.getId().equals(uid));
                List<UserFollow> userFollows = userFollowService.getMyFollower(uid, user.getId());
                model.addAttribute("hadFollow", userFollows.size() > 0);
            }
            session.setAttribute("current_navigation_id", user.getId());
            session.setAttribute("current_user", user);


//            List<UserCategory> userCategories = userCategoryService.selectCategoryByUid(user.getId());
//            model.addAttribute("userCategories", userCategories);
            return "navigation/navigation";
        }
    }

    @RequestMapping("/json/getSession.json")
    @ResponseBody
    public User getSession(HttpSession session) {
        String uid = (String) session.getAttribute("uid");
        String cnid = (String) session.getAttribute("current_navigation_id");
        if (uid != null && cnid != null && uid.equals(cnid)) {
            User user = userService.selectUserById(uid);
            return user;
        }
        return null;
    }
}