package me.happycao.lingxi.service.impl;

import me.happycao.lingxi.constant.Constant;
import me.happycao.lingxi.entity.TFeedAction;
import me.happycao.lingxi.mapper.TFeedActionMapper;
import me.happycao.lingxi.model.Result;
import me.happycao.lingxi.service.FeedActionService;
import me.happycao.lingxi.util.ParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 动态相关操作，0点赞1收藏
 * version: 1.0
 */
@Service
public class FeedActionServiceImpl implements FeedActionService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    TFeedActionMapper tFeedActionMapper;

    @Override
    public Result saveFeedAction(TFeedAction tFeedAction) {
        Result result = Result.success();

        // 参数验证
        Integer type = tFeedAction.getType();
        String feedId = tFeedAction.getFeedId();
        String userId = tFeedAction.getUserId();
        if (type == null || StringUtils.isEmpty(feedId) || StringUtils.isEmpty(userId)) {
            logger.warn("param warn : 必填参数不全");
            result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "必填参数不全");
            return result;
        }

        // 是否已操作
        TFeedAction temp = tFeedActionMapper.selectOne(tFeedAction);
        if (temp == null) {
            String id = ParamUtil.getUUID();
            tFeedAction.setId(id);
            tFeedActionMapper.insertSelective(tFeedAction);

            temp = tFeedActionMapper.selectByPrimaryKey(id);
        }

        // 返回
        result.setData(temp);
        return result;
    }

    @Override
    public Result removeFeedAction(TFeedAction tFeedAction) {
        Result result = Result.success();

        // 参数验证
        Integer type = tFeedAction.getType();
        String feedId = tFeedAction.getFeedId();
        String userId = tFeedAction.getUserId();
        if (type == null || StringUtils.isEmpty(feedId) || StringUtils.isEmpty(userId)) {
            logger.warn("param warn : 必填参数不全");
            result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "必填参数不全");
            return result;
        }

        tFeedActionMapper.delete(tFeedAction);
        return result;
    }
}
