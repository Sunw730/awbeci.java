package com.awbeci.service.spring;

import com.awbeci.dao.IUserCategoryDao;
import com.awbeci.domain.UserCategory;
import com.awbeci.service.IUserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserCagetoryImpl implements IUserCategoryService {
    @Autowired
    private IUserCategoryDao userCategoryDao;


    public List<Map> selectCategoryByUid(String uid, String pid) {
        return userCategoryDao.selectCategoryByUid(uid, pid);
    }


    public List<UserCategory> selectCategoryParent(String uid) {
        return userCategoryDao.selectCategoryParent(uid);
    }


    public int insertCategory(UserCategory userCategory) {
        return userCategoryDao.insertCategory(userCategory);
    }


    public List<UserCategory> selectCategoryChildByPid(String pid) {
        return userCategoryDao.selectCategoryChildByPid(pid);
    }


    public List<UserCategory> selectCategoryChild(String uid) {
        return userCategoryDao.selectCategoryChild(uid);
    }


    public int updateCategoryById(UserCategory userCategory) {
        return userCategoryDao.updateCategoryById(userCategory);
    }


    public int deleteCategory(String id) {
        return userCategoryDao.deleteCategory(id);
    }

}
