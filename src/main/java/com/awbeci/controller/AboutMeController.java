package com.awbeci.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by zhangwei on 2015/6/14.
 */
@Controller
public class AboutMeController {
    @RequestMapping("/aboutme")
    public ModelAndView aboutme(HttpSession session) {
        //Object user = session.getAttribute("user");
        return new ModelAndView("aboutme/aboutme");
    }
}
