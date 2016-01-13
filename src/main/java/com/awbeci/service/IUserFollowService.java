package com.awbeci.service;

import com.awbeci.domain.User;
import com.awbeci.domain.UserFollow;

import java.util.List;

public interface IUserFollowService {

    List<UserFollow> getFollowingByUid(String uid);
}
