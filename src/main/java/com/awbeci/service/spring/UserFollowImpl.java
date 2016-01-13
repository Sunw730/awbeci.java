package com.awbeci.service.spring;

import com.awbeci.dao.IUserDao;
import com.awbeci.dao.IUserFollowDao;
import com.awbeci.domain.UserFollow;
import com.awbeci.service.IUserFollowService;
import com.awbeci.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFollowImpl implements IUserFollowService{

    @Autowired
    private IUserFollowDao userFollowDao;

    public List<UserFollow> getFollowingByUid(String uid) {
        return userFollowDao.getFollowingByUid(uid);
    }
}
