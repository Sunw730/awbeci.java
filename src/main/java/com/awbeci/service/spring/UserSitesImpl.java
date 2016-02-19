package com.awbeci.service.spring;

import com.awbeci.aliyun.oss.BucketObject;
import com.awbeci.dao.IUserDao;
import com.awbeci.dao.IUserSitesDao;
import com.awbeci.domain.UserCategory;
import com.awbeci.domain.UserSites;
import com.awbeci.service.IUserSitesService;
import com.awbeci.util.MyProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserSitesImpl implements IUserSitesService {

    @Autowired
    private IUserSitesDao userSitesDao;

    @Autowired
    private IUserDao userDao;

    Logger log = LoggerFactory.getLogger(BucketObject.class);
    MyProperties myProperties = new MyProperties();


    public List<UserSites> getSiteByCategoryId(String categoryid) {
        return userSitesDao.getSiteByCategoryId(categoryid);
    }

    /**
     * 保存网址
     *
     * @param flag       判断是添加还是修改
     * @param properties 配置文件
     * @param userSites  site实体类
     * @return
     */

    public int saveSite(String flag, String properties, UserSites userSites) {
        try {
            String oldicon = userSites.getIcon();
            Properties prop = myProperties.getPropertiesByName(properties);
            Date date = new Date();
            String searchapi = prop.getProperty("searchapi");
            String out_ossurl = prop.getProperty("out_ossurl");
            String bucketfolder = prop.getProperty("bucketfolder");
            String defaultico = prop.getProperty("defaultico");
            DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String favicon = format.format(new Date()) + ".ico";

            BucketObject bucketObject = new BucketObject(properties);
            //将图片上传到oss(走内网)
            boolean data = bucketObject.putObjectByUrl(bucketfolder, favicon, searchapi + userSites.getUrl());
            String icon = out_ossurl + bucketfolder + favicon;
            if (!data) {
                //设置成默认的图片(走外网)
                icon = out_ossurl + bucketfolder + defaultico;
            }
            userSites.setIcon(icon);
            userSites.setSortNo(1);
            userSites.setUpdateDt(date);

            if (flag.equals("add")) {
                userSites.setId(UUID.randomUUID().toString());
                userSites.setCreateDt(date);
                int val = userSitesDao.insertSite(userSites);
                return val;
            }
            if (flag.equals("update")) {
                String key = bucketfolder + oldicon.split("/")[oldicon.split("/").length - 1];
                //删除原先的icon
                bucketObject.deleteObject(key);
                int val = userSitesDao.updateSite(userSites);
                return val;
            }
            return 0;
        } catch (Exception e) {
            log.error("出错:" + e.getMessage());
            return 0;
        }
    }


    public List<UserSites> getSitesByMostClick(String id) {
        return userSitesDao.getSitesByMostClick(id);
    }

    /**
     * 删除网址
     *
     * @param properties
     * @param id
     * @param iconUrl
     * @return
     */

    public int deleteSite(String properties, String id, String iconUrl) {
        try {
            Properties prop = myProperties.getPropertiesByName(properties);
            String bucketfolder = prop.getProperty("bucketfolder");
            BucketObject bucketObject = new BucketObject(properties);
            if (!iconUrl.equals("")) {
                String key = bucketfolder + iconUrl.split("/")[iconUrl.split("/").length - 1];
                bucketObject.deleteObject(key);
            }
            return userSitesDao.deleteSite(id);
        } catch (Exception e) {
            return 0;
        }
    }


    public List<UserSites> querySiteByParam(String param, String uid) {
        return userSitesDao.querySiteByParam(param, uid);
    }

    /**
     * 上传头像
     *
     * @param properties
     * @param content
     * @param uid
     * @return
     */
    public String uploadAvatar(String properties, InputStream content, String uid, String avatarImgUrl) {
        try {
            Properties prop = myProperties.getPropertiesByName(properties);
            String out_ossurl = prop.getProperty("out_ossurl");
            String bucketfolder = prop.getProperty("avatarBucketFolder");
            DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String favicon = format.format(new Date());

            BucketObject bucketObject = new BucketObject(properties);
            //将图片上传到oss(走内网)
            boolean data = bucketObject.putObjectByFilePath(bucketfolder, favicon, content);
            String avatorImgPath = out_ossurl + bucketfolder + favicon;
            if (data) {
                userDao.updateAvatarUrl(uid, avatorImgPath);
                //如果不为空删除旧头像，并且不删除默认头像
                if (!avatarImgUrl.isEmpty() && avatarImgUrl.indexOf("default") == -1) {
                    String key = bucketfolder + avatarImgUrl.split("/")[avatarImgUrl.split("/").length - 1];
                    bucketObject.deleteObject(key);
                }
                return avatorImgPath;
            }
        } catch (Exception ex) {

        }
        return null;
    }

    public List<UserSites> getUserSitesByCategoryId(String categoryId) {
        return userSitesDao.getUserSitesByCategoryId(categoryId);
    }

    public int getUserSitesCountByUid(String uid) {
        return userSitesDao.getUserSitesCountByUid(uid);
    }

    public List<Map> getTopUrl(String uid) {
        return userSitesDao.getTopUrl(uid);
    }

    public List<UserSites> querySiteByUrl(String param, String uid) {
        return userSitesDao.querySiteByUrl(param, uid);
    }

}
