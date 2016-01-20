package com.awbeci.controller;

import com.awbeci.domain.User;
import com.awbeci.util.MyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.awbeci.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class LoginController {
    MyProperties myProperties = new MyProperties();
    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public String login(HttpSession session) {
        Object sessionuser = session.getAttribute("user");
        if (sessionuser != null) {
            return "redirect:/main";
        }
        return "login/login";
    }


    /**
     * 主页注册
     *
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/region")
    public String region(HttpSession session) throws Exception {
        Object sessionuser = session.getAttribute("user");
        if (sessionuser != null) {
            return "redirect:/main";
        }
        return "/login/region";
    }


    /**
     * 主页注册
     *
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/json/quickRegion.json", method = RequestMethod.POST)
    @ResponseBody
    public String quickRegion(User user, HttpSession session) throws Exception {
        Object sessionuser = session.getAttribute("user");
        String properties = "awbeci.properties";
        if (sessionuser != null) {
            return "/";
        }
        Random random = new Random();
        int result = random.nextInt(10);
        Properties prop = myProperties.getPropertiesByName(properties);
        String avatarDeaultImg = prop.getProperty("avatarDeaultImg");
        user.setAvatarUrl(avatarDeaultImg + (++result) + ".png");

        User haveUserByName = userService.selectUserByName(user.getName());
        User haveUserByEmail = userService.selectUserByEmail(user.getEmail());
        if (haveUserByName != null) {
            return "该用户名已经被注册";
        }
        if (haveUserByEmail != null){
            return "该邮箱已经被注册";
        }
        boolean data = userService.region(user, properties);
        if (data) {
            //设置session
            session.setAttribute("user", user);
            session.setAttribute("userName", user.getName());
            session.setAttribute("uid", user.getId());
            session.setAttribute("current_navigation_id", user.getId());
            //设置session过期时间为一年
            session.setMaxInactiveInterval(60 * 24 * 30);
            return "注册成功";
        } else {
            //邮件发送失败
            return "注册失败";
        }
    }

    /**
     * 验证邮箱
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "validate/{id}", method = RequestMethod.GET)
    public String validate(@PathVariable String id, HttpSession session) {
        Object username = session.getAttribute("userName");
        if (id != null && !id.equals("") && username != null && !username.equals("")) {
            //todo:是否验证了多次，要判断下
            userService.validateEmail(id);
            return "validate/validateEmail";
        }
        //todo:这里可以导向一个新页面：提示用户：请先登录！
        return "redirect:/";
    }

    /**
     * 登录
     *
     * @param name
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/json/loginIn.json")
    @ResponseBody
    public Map<String, Object> loginIn(String name, String password, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.selectUser(name, password);
        if (user != null) {
            session.setAttribute("user", user);
            session.setAttribute("userName", user.getName());
            session.setAttribute("uid", user.getId());
            session.setAttribute("current_navigation_id", user.getId());
            //设置session过期时间为一年
            session.setMaxInactiveInterval(60 * 24 * 30);
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    /**
     * 退出
     */
    @RequestMapping("/json/loginOut.json")
    @ResponseBody
    public Map<String, Object> loginOut(HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        session.invalidate();
        map.put("success", true);
        return map;
    }
}
