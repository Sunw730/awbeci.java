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

    @RequestMapping("/settings/account")
    public ModelAndView login() {
        return new ModelAndView("user/showInfo");
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
}
