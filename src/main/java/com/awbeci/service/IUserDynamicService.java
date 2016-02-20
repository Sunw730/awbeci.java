package com.awbeci.service;

import com.awbeci.domain.UserDynamic;

import java.util.List;

public interface IUserDynamicService {
    int insertUserDynamic(UserDynamic userDynamic);

    List<UserDynamic> selectMyUserDynamic(String uid);
}
