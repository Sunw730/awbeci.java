package com.awbeci.dao;

import com.awbeci.domain.User;
import com.awbeci.domain.UserFollow;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IUserFollowDao {

    @Select("select * from userfollow where uid=#{uid}")
    List<UserFollow> getFollowingByUid(@Param("uid") String uid);
}
