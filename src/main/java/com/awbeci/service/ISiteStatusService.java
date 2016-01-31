package com.awbeci.service;

import com.awbeci.domain.SiteStatus;

import java.util.List;

public interface ISiteStatusService {

    List<SiteStatus> selectAllSiteStatus();

    int insertSiteStatus(SiteStatus siteStatus);

    int updateSiteStatus(SiteStatus siteStatus);

    int deleteSiteStatus(SiteStatus siteStatus);
}
