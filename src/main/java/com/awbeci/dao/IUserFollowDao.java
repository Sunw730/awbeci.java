package com.awbeci.dao;

import com.awbeci.domain.User;
import com.awbeci.domain.UserFollow;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IUserFollowDao {

    /**
     * 查询uid的用户正在关注的数据
     * @param uid
     * @return
     */
    @Select("select a.*,b.id userid,b.name,b.avatarUrl,b.nicename,b.createdt usercreatedt" +
            " from userfollow a " +
            " left join user b on a.followid=b.id" +
            " where a.uid=#{uid}")
    @ResultMap("com.awbeci.mapper.UserFollowMapper.UserFollowResult")
    List<UserFollow> getFollowingByUid(@Param("uid") String uid);

    @Select("select a.*,b.id userid,b.name,b.avatarUrl,b.nicename,b.createdt usercreatedt" +
            " from userfollow a " +
            " left join user b on a.uid=b.id" +
            " where a.followid=#{uid}")
    @ResultMap("com.awbeci.mapper.UserFollowMapper.UserFollowResult")
    List<UserFollow> getFollowerByUid(@Param("uid") String uid);


}
