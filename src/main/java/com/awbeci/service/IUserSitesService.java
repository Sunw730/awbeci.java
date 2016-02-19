package com.awbeci.service;

import com.awbeci.domain.User;
import com.awbeci.domain.UserSites;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface IUserSitesService {

    List<UserSites> getSiteByCategoryId(String categoryid);

    int saveSite(String flag, String properties, UserSites userSites);

    List<UserSites> getSitesByMostClick(String id);

    int deleteSite(String properties, String id, String iconUrl);

    List<UserSites> querySiteByParam(String param, String uid);

    String uploadAvatar(String properties, InputStream content, String uid, String avatarImgUrl);

    List<UserSites> getUserSitesByCategoryId(String categoryId);

    int getUserSitesCountByUid(String uid);

    List<Map> getTopUrl(String uid);

    List<UserSites> querySiteByUrl(String param, String uid);
}
