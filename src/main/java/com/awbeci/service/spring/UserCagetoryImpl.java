package com.awbeci.service.spring;

import com.awbeci.dao.IUserCategoryDao;
import com.awbeci.domain.UserCategory;
import com.awbeci.service.IUserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCagetoryImpl implements IUserCategoryService {
    @Autowired
    private IUserCategoryDao userCategoryDao;

    @Override
    public List<UserCategory> selectCategoryByUid(String uid) {
        return userCategoryDao.selectCategoryByUid(uid);
    }

    @Override
    public List<UserCategory> selectCategoryParent(String uid) {
        return userCategoryDao.selectCategoryParent(uid);
    }

    @Override
    public int insertCategory(UserCategory userCategory) {
        return userCategoryDao.insertCategory(userCategory);
    }

    @Override
    public List<UserCategory> selectCategoryChildByPid(String pid) {
        return userCategoryDao.selectCategoryChildByPid(pid);
    }

    @Override
    public int updateCategoryById(UserCategory userCategory) {
        return userCategoryDao.updateCategoryById(userCategory);
    }

    @Override
    public int deleteCategory(String id) {
        return userCategoryDao.deleteCategory(id);
    }

}
