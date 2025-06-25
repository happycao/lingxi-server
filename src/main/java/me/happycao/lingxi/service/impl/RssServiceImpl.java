package me.happycao.lingxi.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.happycao.lingxi.constant.Constant;
import me.happycao.lingxi.constant.RssConfig;
import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.service.RssService;
import me.happycao.lingxi.util.DateUtil;
import me.happycao.lingxi.util.ParamUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/05/16
 * desc   : 资源服务相关
 * version: 1.0
 */
@Slf4j
@Service
public class RssServiceImpl implements RssService {

    @Resource
    private RssConfig rssConfig;

    /**
     * 用户图片上传
     */
    @Override
    public Result uploadUserImage(MultipartFile[] files) {
        String[] types = new String[]{".jpg", ".png", ".jpeg"};
        return uploadFile(files, rssConfig.getUserPath(), types);
    }

    /**
     * 动态图片上传
     */
    @Override
    public Result uploadFeedImage(MultipartFile[] files) {
        String[] types = new String[]{".jpg", ".jpeg", ".webp", ".png", ".gif"};
        return uploadFile(files, rssConfig.getFeedPath(), types);
    }

    /**
     * 上传文件到本地
     */
    private Result uploadFile(MultipartFile[] files, String typePath, String... types) {
        Result result = Result.success();

        // 此处为返回给前端的访问链接
        // 如果通过nginx访问静态资源，可以给域名或ip的拼接路径，或者给相对路径
        // 如：http://localhost:7070/rss/image/20180516001.png，此为完整访问路径
        // 或：/rss/image/20180516001.png，此为相对路径
        // 我们采用相对路径即typePath
        List<String> urls = new ArrayList<>();

        // 上传目录 | 新增以年月划分的文件夹，减轻一个文件夹保存文件的压力
        typePath = typePath + DateUtil.formatYm(new Date()) + RssConfig.FORWARD_SLASH;
        String dirPath = rssConfig.getUploadPath() + typePath;
        log.warn("upload path : " + dirPath);

        for (MultipartFile multipartFile : files) {
            String fileName = multipartFile.getOriginalFilename();
            if (StringUtils.isBlank(fileName)) {
                continue;
            }

            String fileType = fileName.substring(fileName.lastIndexOf("."));

            if (!verifyType(fileType, types)) {
                log.warn("uploadFile : " + fileName);
                result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "格式不符合要求，只支持" + appendType(types));
                return result;
            }
            File file = new File(dirPath);
            if (!file.exists()) {
                boolean mkdir = file.mkdirs();
                log.debug("mkdir {} {}", file.getPath(), mkdir);
            }

            // 重命名文件
            fileName = ParamUtil.getUUID() + fileType;
            file = new File(dirPath + fileName);

            try {
                // 保存文件
                multipartFile.transferTo(file);
            } catch (IOException e) {
                log.warn("uploadFile : " + e.getMessage(), e);
                result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "上传失败，error：" + e.getMessage());
                return result;
            }

            // url
            String url = typePath + fileName;
            urls.add(url);
        }

        result.setData(urls);
        return result;
    }

    /**
     * 验证文件类型
     */
    private boolean verifyType(String type, String... types) {
        for (String str : types) {
            if (str.equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 拼接类型字符
     */
    private String appendType(String... types) {
        return String.join("和", types);
    }

}