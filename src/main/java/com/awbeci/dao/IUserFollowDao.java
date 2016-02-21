package com.awbeci.dao;

import com.awbeci.domain.User;
import com.awbeci.domain.UserFollow;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserFollowDao {

    /**
     * 查询uid的用户正在关注的数据
     *
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

    @Select("select * from userfollow where uid=#{uid} and followid=#{followid}")
    List<UserFollow> getMyFollower(@Param("uid") String uid, @Param("followid") String followid);

    @Insert("insert into userfollow(`id`,`uid`,`followId`,`followName`,`followUrl`,`type`,`createDt`,`updateDt`)" +
            "values(#{id},#{uid},#{followId},#{followName},#{followUrl},#{type},#{createDt},#{updateDt})")
    int insertUserFollow(UserFollow userFollow);

    @Delete("delete from userfollow where uid=#{uid} and followId=#{followid}")
    int deleteUserFollow(@Param("uid") String uid, @Param("followid") String followid);

    @Select(" select count(*) count " +
            " from userfollow a " +
            " left join user b on a.uid=b.id" +
            " where a.followid=#{uid}")
    int getFollowerByUidCount(@Param("uid") String uid);

    @Select("select count(*) count" +
            " from userfollow a " +
            " left join user b on a.followid=b.id" +
            " where a.uid=#{uid}")
    int getFollowingByUidCount(@Param("uid") String uid);
}
