package com.awbeci.service.spring;

import com.awbeci.dao.IUserDao;
import com.awbeci.domain.User;
import com.awbeci.service.IUserService;
import com.awbeci.util.Email;
import com.awbeci.util.MyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    MyProperties myProperties = new MyProperties();


    public List<User> selectUser() {
        return userDao.selectUser();
    }


    public User selectUser(String name, String passwd) {
        return userDao.selectUserByParam(name, passwd);
    }


    public boolean region(User newUser, String properties) {
        Email emailUtil = new Email(properties);
        Properties prop = myProperties.getPropertiesByName(properties);
        String volidateEmailUrl = prop.getProperty("volidateEmailUrl");

        //设置激活码
        UUID id = UUID.randomUUID();
        newUser.setId(id.toString());
        newUser.setStatus(1);
        newUser.setEmailAble("0");
        //newUser.setUrl(volidateEmailUrl + newUser.getName());
        newUser.setCreateDt(new Timestamp(System.currentTimeMillis()));
        newUser.setUpdateDt(new Timestamp(System.currentTimeMillis()));

        boolean data = emailUtil.sendEmail(newUser.getEmail(), "来自Awbeci的注册邮件",
                "<html><head></head><body><p>您好,<br>" +
                        "感谢您通过Awbeci注册.<br>" +
                        "点击以下链接验证您的邮箱地址：</p>" +
                        "<a href='" + volidateEmailUrl + "validate/" + id + "'>" +
                        volidateEmailUrl + "validate/" + id + "</a>" +
                        "</body></html>");
        //邮件发送成功
        if (data) {
            userDao.insertUser(newUser);
            return true;
        } else {
            return false;
        }
    }

    public int validateEmail(String id) {
        return userDao.updateEmailAble(id);
    }


    public User selectUserByName(String name) {
        return userDao.selectUserByName(name);
    }

    /**
     * 根据id获取用户数据
     * @param id
     * @return
     */
    public User selectUserById(String id) {
        return userDao.selectUserById(id);
    }

    public int updateProfile(User user) {
        return userDao.updateProfile(user);
    }

    /**
     * 更新用户密码
     * @param user
     * @return
     */
    public int updatePassword(User user) {
        return userDao.updatePassword(user);
    }

    public User selectUserByEmail(String email) {
        return userDao.selectUserByEmail(email);
    }

}
