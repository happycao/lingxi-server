package me.happycao.lingxi.service.impl;

import me.happycao.lingxi.entity.TAppVersion;
import me.happycao.lingxi.mapper.TAppVersionMapper;
import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/04/28
 * desc   : app版本
 * version: 1.0
 */
@Service
public class AppVersionServiceImpl implements AppVersionService {

    @Autowired
    private TAppVersionMapper tAppVersionMapper;

    @Override
    public Result latestVersion() {

        Example example = new Example(TAppVersion.class);
        example.setOrderByClause("version_code desc");

        List<TAppVersion> tAppVersions = tAppVersionMapper.selectByExample(example);
        TAppVersion tAppVersion = null;
        if (tAppVersions != null && tAppVersions.size() > 0){
            tAppVersion = tAppVersions.get(0);
        }

        return Result.success(tAppVersion);
    }
}
