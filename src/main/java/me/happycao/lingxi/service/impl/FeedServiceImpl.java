package me.happycao.lingxi.service.impl;

import me.happycao.lingxi.constant.Constant;
import me.happycao.lingxi.dao.FeedDao;
import me.happycao.lingxi.dao.RelevantDao;
import me.happycao.lingxi.entity.TFeedPhoto;
import me.happycao.lingxi.mapper.TFeedMapper;
import me.happycao.lingxi.mapper.TFeedPhotoMapper;
import me.happycao.lingxi.model.Feed;
import me.happycao.lingxi.model.PageInfo;
import me.happycao.lingxi.model.Relevant;
import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.service.FeedService;
import me.happycao.lingxi.util.ParamUtil;
import me.happycao.lingxi.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 动态
 * version: 1.0
 */
@Service
public class FeedServiceImpl implements FeedService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TFeedMapper tFeedMapper;

    @Autowired
    private FeedDao feedDao;

    @Autowired
    private TFeedPhotoMapper tFeedPhotoMapper;

    @Autowired
    private RelevantDao relevantDao;

    @Override
    public Result pageFeed(FeedSearchVO feedSearchVO){
        Result result = Result.success();

        String userId = feedSearchVO.getUserId();
        if (StringUtils.isEmpty(userId)) {
            logger.warn("param warn : userId为空");
            result.setCodeAndMsg(Constant.ERROR_CODE_ID_NULL, "userId为空");
            return result;
        }

        // 分页设置
        ParamUtil.setPage(feedSearchVO);

        Integer total = feedDao.feedTotal(new StateVO(1));
        List<Feed> feedList = feedDao.pageFeed(feedSearchVO);

        // 分页数据
        PageInfo<Feed> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(feedSearchVO.getPageNum());
        pageInfo.setPageSize(feedSearchVO.getPageSize());
        pageInfo.setTotal(total);
        pageInfo.setList(feedList);
        pageInfo.setSize(feedList == null ? 0 : feedList.size());

        result.setData(pageInfo);
        return result;
    }

    @Override
    public Result saveFeed(FeedSaveVO feedSaveVO) {
        Result result = Result.success();

        String userId = feedSaveVO.getUserId();
        String feedInfo = feedSaveVO.getFeedInfo();
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(feedInfo)) {
            logger.warn("param warn : userId或feedInfo为空");
            result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, Constant.ERROR_MSG_PARAM_NULL);
            return result;
        }

        String id = ParamUtil.getUUID();
        feedSaveVO.setId(id);
        tFeedMapper.insertSelective(feedSaveVO);

        List<String> photoList = feedSaveVO.getPhotoList();
        if (photoList != null && photoList.size() != 0) {
            for (String url : photoList) {
                TFeedPhoto tFeedPhoto = new TFeedPhoto();
                tFeedPhoto.setId(ParamUtil.getUUID());
                tFeedPhoto.setFeedId(id);
                tFeedPhoto.setUrl(url);
                tFeedPhotoMapper.insertSelective(tFeedPhoto);
                logger.warn("photo " + url);
            }
        }

        result.setData(tFeedMapper.selectByPrimaryKey(id));
        return result;
    }

    @Override
    public Result viewFeed(IdVO idVO) {
        feedDao.viewFeed(idVO);
        return Result.success();
    }

    @Override
    public Result pageRelevant(RelevantVO relevantVO) {
        Result result = Result.success();

        String userId = relevantVO.getUserId();
        if (StringUtils.isEmpty(userId)) {
            logger.warn("param warn : userId为空");
            result.setCodeAndMsg(Constant.ERROR_CODE_ID_NULL, "userId为空");
            return result;
        }

        // 分页设置
        ParamUtil.setPage(relevantVO);

        Integer total = relevantDao.relevantTotal(new UserIdVO(userId));
        List<Relevant> relevantList = relevantDao.pageRelevant(relevantVO);

        // 分页数据
        PageInfo<Relevant> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(relevantVO.getPageNum());
        pageInfo.setPageSize(relevantVO.getPageSize());
        pageInfo.setTotal(total);
        pageInfo.setList(relevantList);
        pageInfo.setSize(relevantList == null ? 0 : relevantList.size());

        result.setData(pageInfo);
        return result;
    }

    @Override
    public Result pageMineReply(RelevantVO relevantVO) {
        Result result = Result.success();

        String userId = relevantVO.getUserId();
        if (StringUtils.isEmpty(userId)) {
            logger.warn("param warn : userId为空");
            result.setCodeAndMsg(Constant.ERROR_CODE_ID_NULL, "userId为空");
            return result;
        }

        // 分页设置
        ParamUtil.setPage(relevantVO);

        Integer total = relevantDao.mineReplyTotal(new UserIdVO(userId));
        List<Relevant> relevantList = relevantDao.pageMineReply(relevantVO);

        // 分页数据
        PageInfo<Relevant> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(relevantVO.getPageNum());
        pageInfo.setPageSize(relevantVO.getPageSize());
        pageInfo.setTotal(total);
        pageInfo.setList(relevantList);
        pageInfo.setSize(relevantList == null ? 0 : relevantList.size());

        result.setData(pageInfo);
        return result;
    }

}