package com.awbeci.service;

import com.awbeci.domain.UserCategory;

import java.util.List;
import java.util.Map;

public interface IUserCategoryService {
    List<Map> selectCategoryByUid(String uid, String pid);

    List<UserCategory> selectCategoryParent(String uid);

    int insertCategory(UserCategory userCategory);

    List<UserCategory> selectCategoryChildByPid(String pid);

    List<UserCategory> selectCategoryChild(String uid);

    int updateCategoryById(UserCategory userCategory);

    int deleteCategory(String id);

}
