package com.awbeci.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class StatusController {

    @RequestMapping("/status")
    public String status(Model model, HttpSession session) {
        return "status/status";
    }
}
