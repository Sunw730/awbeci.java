package com.awbeci.dao;

import com.awbeci.domain.User;
import com.awbeci.domain.UserCategory;
import com.awbeci.domain.UserSites;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IUserCategoryDao {

    @Select(" select a.id,a.name,a.depth,count(b.id) sitescount,(select count(*)" +
            " from usercategory" +
            " where pid=a.id and uid=#{uid}) categorycount" +
            " from usercategory a" +
            " left join usersites b on a.id=b.categoryid" +
            " where a.uid=#{uid} and pid=#{pid} and a.name like '%${name}%'" +
            " group by a.id,a.name,a.depth" +
            " order by a.sortNo")
    List<Map> selectCategoryByUid(@Param("uid") String uid, @Param("pid") String pid, @Param("name") String name);

    @Select("select * from usercategory " +
            "where uid=#{uid} and ( pid is null or pid = '') " +
            "order by sortNo")
    List<UserCategory> selectCategoryParent(String uid);

    @Select("select * from usercategory where pid=#{pid}")
    List<UserCategory> selectCategoryChildByPid(String pid);

    @Select("select * from usercategory where uid=#{uid}")
    List<UserCategory> selectCategoryChild(String uid);

    @Insert("insert into usercategory(id,uid,pid,name,depth,sortno,createdt,updatedt)" +
            "values(#{id},#{uid},#{pid},#{name},#{depth},#{sortNo},#{createDt},#{updateDt})")
    int insertCategory(UserCategory userCategory);

    @Update("update usercategory " +
            "set name=#{name},pid=#{pid},updateDt=#{updateDt} where id=#{id}")
    int updateCategoryById(UserCategory userCategory);

    @Delete("delete from usercategory where id=#{id}")
    int deleteCategory(String id);

    @Select("select * from usercategory where name like '%${param}%' and uid=#{uid}")
    List<UserCategory> queryCategoryByParam(@Param("param") String param, @Param("uid") String uid);
}
