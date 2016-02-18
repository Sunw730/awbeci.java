package com.awbeci.dao;

import com.awbeci.domain.User;
import com.awbeci.domain.UserSites;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("IUserSitesDao")
public interface IUserSitesDao {

    @Select("select * from usersites where categoryId=#{categoryId}")
    List<UserSites> getSiteByCategoryId(String categoryId);

    @Insert("insert into usersites(id,categoryId,uId,name,url,icon,sortno,createdt,updatedt)" +
            "values(#{id},#{categoryId},#{uId},#{name},#{url},#{icon},#{sortNo},#{createDt},#{updateDt})")
    int insertSite(UserSites userSites);

    @Update("update usersites set " +
            "categoryId=#{categoryId},uId=#{uId},name=#{name},url=#{url},icon=#{icon},sortno=#{sortNo},updatedt=#{updateDt} " +
            "where id=#{id}")
    int updateSite(UserSites userSites);

    @Select("select * from usersites where uId=#{uId} limit 0,100")
    List<UserSites> getSitesByMostClick(String uId);

    @Delete("delete from usersites where id=#{id}")
    int deleteSite(String id);

    @Select("select * from usersites where name like '%${param}%' and uId=#{uid}")
    List<UserSites> querySiteByParam(@Param("param") String param, @Param("uid") String uid);


    @Select("select * from usersites where categoryId=#{categoryId}")
    List<UserSites> getUserSitesByCategoryId(@Param("categoryId") String categoryId);

    @Select("SELECT count(*) count FROM usersites " +
            "where uid = #{uid}")
    int getUserSitesCountByUid(@Param("uid") String uid);

    @Select("SELECT fn_get_topDomainName_from_url(url) topUrl,count(fn_get_topDomainName_from_url(url))  topUrlCount,icon " +
            " FROM usersites " +
            " where uid=#{uid} " +
            " group by topUrl " +
            " order by topUrlCount desc " +
            " limit 0,50")
    List<Map> getTopUrl(@Param("uid") String uid);
}
