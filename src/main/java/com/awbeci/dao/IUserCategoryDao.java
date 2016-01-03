package com.awbeci.dao;

import com.awbeci.domain.User;
import com.awbeci.domain.UserCategory;
import com.awbeci.domain.UserSites;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IUserCategoryDao {

    @Select("select * from usercategory where uid=#{uid}" +
            " order by sortNo")
    List<UserCategory> selectCategoryByUid(String uid);

    @Select("select * from usercategory " +
            "where uid=#{uid} and ( pid is null or pid = '') " +
            "order by sortNo")
    List<UserCategory> selectCategoryParent(String uid);

    @Select("select * from usercategory where pid=#{pid}")
    List<UserCategory> selectCategoryChildByPid(String pid);

    @Select("select * from usercategory where uid=#{uid}")
    List<UserCategory> selectCategoryChild(String uid);

    @Insert("insert into usercategory(id,uid,pid,name,sortno,createdt,updatedt)" +
            "values(#{id},#{uid},#{pid},#{name},#{sortNo},#{createDt},#{updateDt})")
    int insertCategory(UserCategory userCategory);

    @Update("update usercategory " +
            "set name=#{name},pid=#{pid},updateDt=#{updateDt} where id=#{id}")
    int updateCategoryById(UserCategory userCategory);

    @Delete("delete from usercategory where id=#{id}")
    int deleteCategory(String id);
}
