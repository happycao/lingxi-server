package me.happycao.lingxi.controller;

import me.happycao.lingxi.model.Result;
import me.happycao.lingxi.service.RssService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/05/16
 * desc   : 资源服务相关
 * version: 1.0
 */
@RestController
@RequestMapping("/rss/upload")
public class RssController {

    private static Logger logger = LoggerFactory.getLogger(RssController.class);

    @Autowired
    private RssService mssService;

    /**
     * pdf上传
     */
    @RequestMapping(value = "/pdf", method = RequestMethod.POST)
    @ResponseBody
    public Result uploadHotelContract(@RequestParam("file") MultipartFile[] files) {

        if (files == null) return Result.paramIsNull();

        logger.info("/upload/pdf param:" + files.length);

        return mssService.uploadPdf(files);
    }

    /**
     * 图片上传
     */
    @RequestMapping(value = "/image", method = RequestMethod.POST)
    @ResponseBody
    public Result uploadFeedbackImage(@RequestParam("file") MultipartFile[] files) {

        if (files == null) return Result.paramIsNull();

        logger.info("/upload/image param:" + files.length);

        return mssService.uploadImage(files);
    }
}
