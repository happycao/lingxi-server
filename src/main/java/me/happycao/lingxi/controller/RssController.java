package me.happycao.lingxi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.service.RssService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/05/16
 * desc   : 资源服务相关
 * version: 1.0
 */
@Api(tags = "05-rss-upload", value = "RssApi", description = "资源上传相关接口")
@RestController
@RequestMapping("/rss/upload")
public class RssController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private RssService mssService;

    /**
     * 用户图片上传
     */
    @ApiOperation(value = "用户图片上传", notes = "用户图片上传接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "X-App-Token", value = "token", required = true),
            @ApiImplicitParam(paramType="form", name = "file", value = "图片文件 - 不可测试", required = true, dataType = "File", allowMultiple = true)
    })
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
    @ApiOperation(value = "动态图片上传", notes = "动态图片上传接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "X-App-Token", value = "token", required = true),
            @ApiImplicitParam(paramType="form", name = "file", value = "图片文件 - 不可测试", required = true, dataType = "File", allowMultiple = true)
    })
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
