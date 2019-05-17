package me.happycao.lingxi.service.impl;

import me.happycao.lingxi.constant.Constant;
import me.happycao.lingxi.entity.TFeedAction;
import me.happycao.lingxi.mapper.TFeedActionMapper;
import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.service.FeedActionService;
import me.happycao.lingxi.util.ParamUtil;
import me.happycao.lingxi.vo.FeedActionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 动态相关操作，0点赞1收藏
 * version: 1.0
 */
@Service
public class FeedActionServiceImpl implements FeedActionService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private TFeedActionMapper tFeedActionMapper;

    @Override
    public Result saveFeedAction(FeedActionVO feedActionVO, String userId) {
        Result result = Result.success();

        // 参数验证
        Integer type = feedActionVO.getType();
        String feedId = feedActionVO.getFeedId();
        if (type == null || StringUtils.isEmpty(feedId)) {
            logger.warn("param warn : 必填参数不全");
            result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "必填参数不全");
            return result;
        }

        // 是否已操作
        TFeedAction param = new TFeedAction();
        param.setType(type);
        param.setFeedId(feedId);
        param.setUserId(userId);
        TFeedAction temp = tFeedActionMapper.selectOne(param);
        if (temp == null) {
            String id = ParamUtil.getUUID();
            param.setId(id);
            tFeedActionMapper.insertSelective(param);

            temp = tFeedActionMapper.selectByPrimaryKey(id);
        }

        // 返回
        result.setData(temp);
        return result;
    }

    @Override
    public Result removeFeedAction(FeedActionVO feedActionVO, String userId) {
        Result result = Result.success();

        // 参数验证
        Integer type = feedActionVO.getType();
        String feedId = feedActionVO.getFeedId();
        if (type == null || StringUtils.isEmpty(feedId)) {
            logger.warn("param warn : 必填参数不全");
            result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "必填参数不全");
            return result;
        }

        TFeedAction param = new TFeedAction();
        param.setType(type);
        param.setFeedId(feedId);
        param.setUserId(userId);
        tFeedActionMapper.delete(param);
        return result;
    }
}
