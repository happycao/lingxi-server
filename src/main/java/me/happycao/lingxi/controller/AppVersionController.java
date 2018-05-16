package me.happycao.lingxi.controller;

import me.happycao.lingxi.model.Result;
import me.happycao.lingxi.service.AppVersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/04/28
 * desc   : app版本
 * version: 1.0
 */
@RestController
@RequestMapping("/app/version")
public class AppVersionController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AppVersionService appVersionService;

    @RequestMapping(value = "/latest", method = RequestMethod.POST)
    @ResponseBody
    public Result latestVersion() {
        logger.warn("/app/version/latest");
        return appVersionService.latestVersion();
    }

}
