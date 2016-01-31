package com.awbeci.controller;

import com.awbeci.domain.SiteStatus;
import com.awbeci.service.ISiteStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Controller
public class StatusController {

    @Autowired
    public ISiteStatusService siteStatusService;

    @RequestMapping("/status")
    public String status(Model model, HttpSession session) {
        return "status/status";
    }

    @RequestMapping("/json/saveStatus.json")
    @ResponseBody
    public String saveStatus(SiteStatus siteStatus, HttpSession session){
        try{
            siteStatus.setId(UUID.randomUUID().toString());
            siteStatus.setCreateDt(new Timestamp(System.currentTimeMillis()));
            siteStatus.setUpdateDt(new Timestamp(System.currentTimeMillis()));
            int d = siteStatusService.insertSiteStatus(siteStatus);
            return "success";
        }
        catch (Exception e){
            return "error";
        }
    }

    @RequestMapping("/json/getAllStatus.json")
    @ResponseBody
    public List getAllStatus(HttpSession session){
        try{
            List data = siteStatusService.selectAllSiteStatus();
            return data;
        }
        catch (Exception e){
            return null;
        }
    }

    @RequestMapping("/json/updateStatus.json")
    @ResponseBody
    public String updateStatus(SiteStatus siteStatus, HttpSession session){
        try{
            siteStatus.setUpdateDt(new Timestamp(System.currentTimeMillis()));
            int d = siteStatusService.updateSiteStatus(siteStatus);
            return "success";
        }
        catch (Exception e){
            return "error";
        }
    }

    @RequestMapping("/json/deleteStatus.json")
    @ResponseBody
    public String deleteStatus(SiteStatus siteStatus, HttpSession session){
        try{
            int d = siteStatusService.deleteSiteStatus(siteStatus);
            return "success";
        }
        catch (Exception e){
            return "error";
        }
    }
}
