package com.awbeci.dao;

import org.apache.ibatis.annotations.Param;
import com.awbeci.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IUserDao {
    @Select("select * from user")
    @ResultMap("com.awbeci.mapper.UserMapper.UserResult")
    List<User> selectUser();

    @Select("select * from user where name=#{name} and password=#{password} or (emailAble='1' and email=#{name})  ")
    User selectUserByParam(@Param("name") String name, @Param("password") String passwd);

    @Insert("insert into user(id,name,password,avatarUrl,niceName,email,emailAble,url,location,status,deleted,createDt,updateDt)" +
            "values(#{id},#{name},#{password},#{avatarUrl},#{niceName},#{email},#{emailAble},#{url},#{location},#{status},#{deleted},#{createDt},#{updateDt})")
    int insertUser(User user);

    @Update("update user set avatarUrl=#{avatarUrl} where id=#{id}")
    int updateAvatarUrl(@Param("id") String id, @Param("avatarUrl") String avatarUrl);

    @Update("update user set emailAble='1' where id=#{id}")
    int updateEmailAble(@Param("id") String id);

    @Select("select * from user where name=#{name}")
    User selectUserByName(@Param("name") String name);

    @Select("select * from user where id=#{id}")
    User selectUserById(@Param("id") String id);

    @Update("update user set name=#{name},niceName=#{niceName},email=#{email},url=#{url},location=#{location} where id=#{id}")
    int updateProfile(User user);

    @Update("update user set password=#{password} where id=#{id}")
    int updatePassword(User user);


}
