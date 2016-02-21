package com.awbeci.service;

import com.awbeci.domain.User;
import com.awbeci.domain.UserFollow;

import java.util.List;

public interface IUserFollowService {

    List<UserFollow> getFollowingByUid(String uid);

    List<UserFollow> getFollowerByUid(String uid);

    List<UserFollow> getMyFollower(String uid, String followid);

    int insertUserFollow(UserFollow userFollow);

    int deleteUserFollow(String uid, String followid);

    int getFollowerByUidCount(String uid);

    int getFollowingByUidCount(String uid);
}
