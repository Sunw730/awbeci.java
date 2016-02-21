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
public class UserFollowImpl implements IUserFollowService {

    @Autowired
    private IUserFollowDao userFollowDao;

    public List<UserFollow> getFollowingByUid(String uid) {
        return userFollowDao.getFollowingByUid(uid);
    }

    public List<UserFollow> getFollowerByUid(String uid) {
        return userFollowDao.getFollowerByUid(uid);
    }

    public List<UserFollow> getMyFollower(String uid, String followid) {
        return userFollowDao.getMyFollower(uid, followid);
    }

    public int insertUserFollow(UserFollow userFollow) {
        return userFollowDao.insertUserFollow(userFollow);
    }

    public int deleteUserFollow(String uid, String followid) {
        return userFollowDao.deleteUserFollow(uid, followid);
    }

    public int getFollowerByUidCount(String uid) {
        return userFollowDao.getFollowerByUidCount(uid);
    }

    public int getFollowingByUidCount(String uid) {
        return userFollowDao.getFollowingByUidCount(uid);
    }
}
