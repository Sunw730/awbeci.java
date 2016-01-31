package com.awbeci.dao;

import com.awbeci.domain.SiteStatus;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ISiteStatusDao {

    @Select("select * from sitestatus order by updateDt desc")
    List<SiteStatus> selectAllSiteStatus();

    @Insert("insert into sitestatus(id,type,content,createDt,updateDt)" +
            " values(#{id},#{type},#{content},#{createDt},#{updateDt})")
    int insertSiteStatus(SiteStatus siteStatus);

    @Update("update sitestatus " +
            "set type=#{type},content=#{content},updateDt=#{updateDt} " +
            "where id=#{id}")
    int updateSiteStatus(SiteStatus siteStatus);

    @Delete("delete from sitestatus where id=#{id}")
    int deleteSiteStatus(SiteStatus siteStatus);
}
