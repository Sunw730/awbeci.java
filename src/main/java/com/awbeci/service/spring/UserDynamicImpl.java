package com.awbeci.service.spring;

import com.awbeci.dao.IUserDynamicDao;
import com.awbeci.service.IUserDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDynamicImpl implements IUserDynamicService{
    @Autowired
    IUserDynamicDao userDynamicDao;
}
