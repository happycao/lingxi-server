package me.happycao.lingxi.service.impl;

import me.happycao.lingxi.constant.Constant;
import me.happycao.lingxi.dao.FeedDao;
import me.happycao.lingxi.dao.RelevantDao;
import me.happycao.lingxi.entity.*;
import me.happycao.lingxi.mapper.*;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 动态
 * version: 1.0
 */
@Service
public class FeedServiceImpl implements FeedService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private TFeedMapper tFeedMapper;

    @Resource
    private FeedDao feedDao;

    @Resource
    private TFeedPhotoMapper tFeedPhotoMapper;

    @Resource
    private RelevantDao relevantDao;

    @Resource
    private TUserMapper tUserMapper;

    @Override
    public Result pageFeed(FeedSearchVO feedSearchVO, String userId) {
        Result result = Result.success();

        ParamUtil.setPage(feedSearchVO);

        Integer total = feedDao.feedTotal(feedSearchVO);
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

    /**
     * 保存用户动态
     * @param feedSaveVO 用户动态
     * @param userId 用户id
     */
    @Transactional
    @Override
    public Result saveFeed(FeedSaveVO feedSaveVO, String userId) {
        Result result = Result.success();

        String feedInfo = feedSaveVO.getFeedInfo();
        if (StringUtils.isEmpty(feedInfo)) {
            logger.warn("param warn : feedInfo为空");
            result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, Constant.ERROR_MSG_PARAM_NULL);
            return result;
        }

        // 保存动态
        String feedId = ParamUtil.getUUID();
        TFeed tFeed = new TFeed();
        tFeed.setId(feedId);
        tFeed.setState(1);
        tFeed.setUserId(userId);
        tFeed.setFeedInfo(feedInfo);
        tFeedMapper.insertSelective(tFeed);

        // 保存图片
        List<String> photoList = feedSaveVO.getPhotoList();
        if (photoList != null && photoList.size() != 0) {
            for (String url : photoList) {
                TFeedPhoto tFeedPhoto = new TFeedPhoto();
                tFeedPhoto.setId(ParamUtil.getUUID());
                tFeedPhoto.setFeedId(feedId);
                tFeedPhoto.setUrl(url);
                tFeedPhotoMapper.insertSelective(tFeedPhoto);
                logger.info("photo {}", url);
            }
        }

        result.setData(tFeedMapper.selectByPrimaryKey(feedId));
        return result;
    }

    @Override
    public Result viewFeed(IdVO idVO) {
        feedDao.viewFeed(idVO);
        return Result.success();
    }

    /**
     * 获取与我相关
     * @param relevantVO 参数
     * @param userId 用户id
     */
    @Override
    public Result pageRelevant(RelevantVO relevantVO, String userId) {
        Result result = Result.success();

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

    /**
     * 获取我的回复
     * @param relevantVO 参数
     * @param userId 用户id
     */
    @Override
    public Result pageMineReply(RelevantVO relevantVO, String userId) {
        Result result = Result.success();

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