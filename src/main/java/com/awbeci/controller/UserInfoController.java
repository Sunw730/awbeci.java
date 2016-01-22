package com.awbeci.controller;

import com.awbeci.domain.User;
import com.awbeci.domain.UserFollow;
import com.awbeci.service.IUserFollowService;
import com.awbeci.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserInfoController {

    Logger log = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    IUserService userService;

    @Autowired
    IUserFollowService userFollowService;

    @RequestMapping("/{username}")
    public String mymain(@PathVariable String username, HttpSession session, Model model) {
        User user = userService.selectUserByName(username);
        if (user == null) {
            return "error/404";
        } else {
            session.setAttribute("current_navigation_id", user.getId());
            session.setAttribute("current_user", user);
            List<UserFollow> followingUsers = userFollowService.getFollowingByUid(user.getId());
            List<UserFollow> followers = userFollowService.getFollowerByUid(user.getId());
            model.addAttribute("current_user", user);
            model.addAttribute("followings", followingUsers);
            model.addAttribute("followingsCount", followingUsers.size());

            model.addAttribute("followers", followers);
            model.addAttribute("followersCount", followers.size());
            Object uidObj = session.getAttribute("uid");
            if (uidObj != null) {
                String uid = uidObj.toString();
                model.addAttribute("isme", user.getId().equals(uid));
                List<UserFollow> userFollows = userFollowService.getMyFollower(uid, user.getId());
                model.addAttribute("hadFollow", userFollows.size() > 0);
            }
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


    //----------------------------------------添加关注----------------------------------------------------------
    @RequestMapping(value = "/json/addFollow.json", method = RequestMethod.POST)
    @ResponseBody
    public int addFollow(UserFollow userFollow, HttpSession session) {
        try {
            Object useridObj = session.getAttribute("uid");
            if (useridObj != null) {
                userFollow.setId(UUID.randomUUID().toString());
                userFollow.setUid(useridObj.toString());
                userFollow.setCreateDt(new Timestamp(System.currentTimeMillis()));
                userFollow.setUpdateDt(new Timestamp(System.currentTimeMillis()));
                return userFollowService.insertUserFollow(userFollow);
            }
        } catch (Exception e) {
            log.debug("错误原因:" + e.getMessage());
        }
        return 0;
    }


    /**
     * 取消关注
     * @param followid
     * @param session
     * @return
     */
    @RequestMapping(value = "/json/removeFollow.json", method = RequestMethod.POST)
    @ResponseBody
    public int removeFollow(String followid, HttpSession session) {
        try {
            Object useridObj = session.getAttribute("uid");
            if (useridObj != null) {
                String uid = useridObj.toString();
                return userFollowService.deleteUserFollow(uid, followid);
            }
        } catch (Exception e) {
            log.debug("错误原因:" + e.getMessage());
        }
        return 0;
    }
}
