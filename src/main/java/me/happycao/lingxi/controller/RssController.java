package me.happycao.lingxi.controller;

import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.service.RssService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Bafs
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
     * 用户图片上传
     */
    @RequestMapping(value = "/user/image", method = RequestMethod.POST)
    @ResponseBody
    public Result uploadHotelContract(@RequestParam("file") MultipartFile[] files) {

        if (files == null) {
            return Result.paramIsNull();
        }

        logger.info("/user/image param:" + files.length);

        return mssService.uploadUserImage(files);
    }

    /**
     * 动态图片上传
     */
    @RequestMapping(value = "/feed/image", method = RequestMethod.POST)
    @ResponseBody
    public Result uploadFeedbackImage(@RequestParam("file") MultipartFile[] files) {

        if (files == null) {
            return Result.paramIsNull();
        }

        logger.info("/feed/image param:" + files.length);

        return mssService.uploadFeedImage(files);
    }
}
