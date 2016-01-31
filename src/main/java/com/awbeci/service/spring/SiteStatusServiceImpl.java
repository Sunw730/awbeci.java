package com.awbeci.service.spring;

import com.awbeci.dao.ISiteStatusDao;
import com.awbeci.domain.SiteStatus;
import com.awbeci.service.ISiteStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteStatusServiceImpl implements ISiteStatusService {

    @Autowired
    private ISiteStatusDao siteStatusDao;

    public List<SiteStatus> selectAllSiteStatus() {
        return siteStatusDao.selectAllSiteStatus();
    }

    public int insertSiteStatus(SiteStatus siteStatus) {
        return siteStatusDao.insertSiteStatus(siteStatus);
    }

    public int updateSiteStatus(SiteStatus siteStatus) {
        return siteStatusDao.updateSiteStatus(siteStatus);
    }

    public int deleteSiteStatus(SiteStatus siteStatus) {
        return siteStatusDao.deleteSiteStatus(siteStatus);
    }
}
