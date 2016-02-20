package com.awbeci.dao;

import com.awbeci.domain.User;
import com.awbeci.domain.UserDynamic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDynamicDao {

    @Insert("insert into userdynamic" +
            "(id,uid,objId,objType,action,content,createDt,updateDt)" +
            "values" +
            "(#{id},#{uid},#{objId},#{objType},#{action},#{content},#{createDt},#{updateDt})")
    int insertUserDynamic(UserDynamic userDynamic);

    //todo:
    @Select(" SELECT distinct a.*,b.id userid,b.name userName,a.createdt,b.AVATARURL" +
            " FROM userdynamic a" +
            " left join user b on a.uid= b.id" +
            " left join usersites c on c.uid=a.uid" +
            " where a.UID in (select followid from userfollow where uid=#{uid}) or a.uid=#{uid}" +
            " order by CREATEDT desc")
    @ResultMap("com.awbeci.mapper.UserDynamicMapper.UserDynamicResult")
    List<UserDynamic> selectMyUserDynamic(@Param("uid") String uid);
}
