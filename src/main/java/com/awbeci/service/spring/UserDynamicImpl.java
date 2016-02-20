package com.awbeci.service.spring;

import com.awbeci.dao.IUserDynamicDao;
import com.awbeci.domain.UserDynamic;
import com.awbeci.service.IUserDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDynamicImpl implements IUserDynamicService{
    @Autowired
    IUserDynamicDao userDynamicDao;

    public int insertUserDynamic(UserDynamic userDynamic) {
        return userDynamicDao.insertUserDynamic(userDynamic);
    }

    public List<UserDynamic> selectMyUserDynamic(String uid) {
        return userDynamicDao.selectMyUserDynamic(uid);
    }
}
