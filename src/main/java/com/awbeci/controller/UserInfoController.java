package com.awbeci.controller;

import com.awbeci.domain.User;
import com.awbeci.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangwei on 2015/10/8.
 */
@Controller
public class UserInfoController {

    Logger log = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    IUserService userService;

    @RequestMapping("/{username}")
    public String  mymain(@PathVariable String username, HttpSession session) {
        User data = userService.selectUserByName(username);
        if (data == null) {
            return "error/404";
        } else {
            session.setAttribute("current_navigation_id", data.getId());
            return "user/mymain";
        }
    }

    @RequestMapping("/settings/account")
    public ModelAndView login(HttpSession session) {
        String uid = (String) session.getAttribute("uid");
        if (uid != null) {
            return new ModelAndView("user/showInfo");
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/following")
    public ModelAndView following() {
        return new ModelAndView("user/following");
    }

    @RequestMapping("/followers")
    public ModelAndView followers() {
        return new ModelAndView("user/followers");
    }

    @RequestMapping(value = "/json/getUserInfo.json", method = RequestMethod.POST)
    @ResponseBody
    public User querySiteByParam(HttpSession session) {
        try {
            String uid = (String) session.getAttribute("uid");
            User user = userService.selectUserById(uid);
            return user;
        } catch (Exception e) {
            log.debug("错误原因:" + e.getMessage());
            return null;
        }
    }

    /**
     * 更新用户资料
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/json/updateProfile.json", method = RequestMethod.POST)
    @ResponseBody
    public int updateProfile(User user, HttpSession session) {
        try {
            String uid = (String) session.getAttribute("uid");
            user.setId(uid);
            int result = userService.updateProfile(user);
            return result;
        } catch (Exception e) {
            log.debug("错误原因:" + e.getMessage());
            return 0;
        }
    }


    @RequestMapping(value = "/json/updatePassword.json", method = RequestMethod.POST)
    @ResponseBody
    public int updatePassword(String id, String oldPwd, String newPwd, String oldPwd2, HttpSession session) {
        try {
            String uid = (String) session.getAttribute("uid");
            User user = userService.selectUserById(uid);
            user.setPassword(newPwd);
            if (user != null) {
                return userService.updatePassword(user);
            }
            return 0;
        } catch (Exception e) {
            log.debug("错误原因:" + e.getMessage());
            return 0;
        }
    }
}
