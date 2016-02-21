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

    @Select(" SELECT " +
            " a.uid         as dynamic_user_id," +
            " a.objid       as dynamic_userSites_id," +
            " a.objtype     as dynamic_userSites_type," +
            " a.action      as dynamic_action," +
            " date_format(a.createdt,'%Y-%m-%d %T')    as dynamic_createdt," +
            " b.id          as user_id," +
            " b.name        as user_name," +
            " b.AVATARURL   as user_AVATARURL," +
            " c.id          as userSites_id," +
            " c.name        as userSites_name," +
            " c.icon        as userSites_icon," +
            " c.url         as userSites_url" +
            " FROM userdynamic a" +
            " left outer join user b on a.uid= b.id" +
            " left outer join usersites c on c.id=a.objid" +
            " where a.UID in (select followid from userfollow where uid=#{uid}) or a.uid=#{uid}" +
            " order by a.CREATEDT desc" +
            " limit 0,100")
    @ResultMap("com.awbeci.mapper.UserDynamicMapper.UserDynamicResultComplex")
    List<UserDynamic> selectMyUserDynamic(@Param("uid") String uid);
}
