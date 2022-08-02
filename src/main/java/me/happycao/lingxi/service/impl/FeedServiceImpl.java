package me.happycao.lingxi.service.impl;

import me.happycao.lingxi.constant.Constant;
import me.happycao.lingxi.dao.FeedDao;
import me.happycao.lingxi.dao.RelevantDao;
import me.happycao.lingxi.entity.*;
import me.happycao.lingxi.mapper.*;
import me.happycao.lingxi.model.Feed;
import me.happycao.lingxi.model.PageInfo;
import me.happycao.lingxi.model.Relevant;
import me.happycao.lingxi.model.Topic;
import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.service.FeedService;
import me.happycao.lingxi.util.ParamUtil;
import me.happycao.lingxi.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private TFeedMapper tFeedMapper;

    @Resource
    private FeedDao feedDao;

    @Resource
    private TFeedPhotoMapper tFeedPhotoMapper;

    @Resource
    private RelevantDao relevantDao;

    @Resource
    private TFeedCommentMapper tFeedCommentMapper;

    @Resource
    private TTopicMapper tTopicMapper;

    @Resource
    private TFeedTopicMapper tFeedTopicMapper;

    @Resource
    private TUserMapper tUserMapper;

    @Resource
    private TFeedAtMapper tFeedAtMapper;

    @Override
    public Result pageFeed(FeedSearchVO feedSearchVO, String userId) {
        Result result = Result.success();

        ParamUtil.setPage(feedSearchVO);

        feedSearchVO.setUserId(userId);
        Integer total = feedDao.feedTotal(feedSearchVO);
        List<Feed> feedList = feedDao.pageFeed(feedSearchVO);

        // 分页数据
        PageInfo<Feed> pageInfo = new PageInfo<>();
        ParamUtil.setPageInfo(pageInfo, feedSearchVO, total, feedList);

        result.setData(pageInfo);
        return result;
    }

    /**
     * 保存用户动态
     *
     * @param feedSaveVO 用户动态
     * @param userId     用户id
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
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

        // 处理话题
        saveTopics(feedInfo, feedId);

        // 处理@
        saveAts(feedInfo, feedId);

        result.setData(tFeedMapper.selectByPrimaryKey(feedId));
        return result;
    }

    /**
     * 保存##相关
     *
     * @param feedInfo 动态详情
     * @param feedId   动态id
     */
    private void saveTopics(String feedInfo, String feedId) {
        List<String> topics = ParamUtil.getTopics(feedInfo);
        List<String> topicIds = new ArrayList<>();
        if (topics.size() > 0) {
            Example topicExample = new Example(TTopic.class);
            Example.Criteria criteria = topicExample.createCriteria();
            criteria.andEqualTo("type", 1);
            criteria.andIn("topic", topics);
            List<TTopic> tTopics = tTopicMapper.selectByExample(topicExample);
            if (tTopics != null && tTopics.size() > 0) {
                for (TTopic tTopic : tTopics) {
                    topics.remove(tTopic.getTopic());
                    topicIds.add(tTopic.getId());
                }
            }
            if (topics.size() > 0) {
                for (String topic : topics) {
                    String topicId = ParamUtil.getUUID();
                    TTopic tTopic = new TTopic();
                    tTopic.setId(topicId);
                    tTopic.setTopic(topic);
                    tTopic.setType(1);
                    tTopic.setState(1);
                    tTopicMapper.insertSelective(tTopic);
                    topicIds.add(topicId);
                }
            }
        }
        // 有话题保存关系
        if (topicIds.size() > 0) {
            for (String topicId : topicIds) {
                TFeedTopic tFeedTopic = new TFeedTopic();
                tFeedTopic.setId(ParamUtil.getUUID());
                tFeedTopic.setFeedId(feedId);
                tFeedTopic.setTopicId(topicId);
                tFeedTopic.setState(1);
                tFeedTopicMapper.insertSelective(tFeedTopic);
            }
        }
    }

    /**
     * 保存@相关
     *
     * @param feedInfo 动态详情
     * @param feedId   动态id
     */
    private void saveAts(String feedInfo, String feedId) {
        List<String> ats = ParamUtil.getAts(feedInfo);
        List<String> atIds = new ArrayList<>();
        if (ats.size() > 0) {
            Example atExample = new Example(TUser.class);
            Example.Criteria criteria = atExample.createCriteria();
            criteria.andIn("username", ats);
            List<TUser> tUsers = tUserMapper.selectByExample(atExample);
            if (tUsers.size() > 0) {
                for (TUser tUser : tUsers) {
                    atIds.add(tUser.getId());
                }
            }
        }
        // 有@保存关系
        if (atIds.size() > 0) {
            for (String atId : atIds) {
                TFeedAt tFeedAt = new TFeedAt();
                tFeedAt.setId(ParamUtil.getUUID());
                tFeedAt.setFeedId(feedId);
                tFeedAt.setAtUserId(atId);
                tFeedAt.setState(1);
                tFeedAtMapper.insertSelective(tFeedAt);
            }
        }
    }

    @Override
    public Result viewFeed(IdVO idVO) {
        feedDao.viewFeed(idVO);
        return Result.success();
    }

    /**
     * 获取与我相关
     *
     * @param relevantVO 参数
     * @param userId     用户id
     */
    @Override
    public Result pageRelevant(RelevantVO relevantVO, String userId) {
        Result result = Result.success();

        relevantVO.setUserId(userId);
        ParamUtil.setPage(relevantVO);

        Integer total = relevantDao.relevantTotal(new UserIdVO(userId));
        List<Relevant> relevantList = relevantDao.pageRelevant(relevantVO);

        // 分页数据
        PageInfo<Relevant> pageInfo = new PageInfo<>();
        ParamUtil.setPageInfo(pageInfo, relevantVO, total, relevantList);

        result.setData(pageInfo);
        return result;
    }

    /**
     * 获取我的回复
     *
     * @param relevantVO 参数
     * @param userId     用户id
     */
    @Override
    public Result pageMineReply(RelevantVO relevantVO, String userId) {
        Result result = Result.success();

        relevantVO.setUserId(userId);
        ParamUtil.setPage(relevantVO);

        Integer total = relevantDao.mineReplyTotal(new UserIdVO(userId));
        List<Relevant> relevantList = relevantDao.pageMineReply(relevantVO);

        // 分页数据
        PageInfo<Relevant> pageInfo = new PageInfo<>();
        ParamUtil.setPageInfo(pageInfo, relevantVO, total, relevantList);

        result.setData(pageInfo);
        return result;
    }

    /**
     * 删除动态
     * @param idVO 参数
     * @param userId 用户id
     */
    @Override
    public Result removeFeed(IdVO idVO, String userId) {
        String feedId = idVO.getId();
        TFeed tFeed = tFeedMapper.selectByPrimaryKey(feedId);
        if (tFeed == null) {
            return Result.objIsNull("动态不存在");
        }
        Integer state = tFeed.getState();
        if (state != 1) {
            return Result.objIsNull("动态不存在或已删除");
        }
        String feedUserId = tFeed.getUserId();
        if (!userId.equals(feedUserId)) {
            return Result.error("无法操作他人动态");
        }

        tFeed.setState(2);
        tFeed.setUpdateTime(new Date());
        tFeedMapper.updateByPrimaryKeySelective(tFeed);
        return Result.success();
    }

    /**
     * 话题查询
     */
    @Override
    public Result queryTopic(NameSearchVO nameSearchVO) {
        Result result = Result.success();

        ParamUtil.setPage(nameSearchVO);

        Integer total = feedDao.topicTotal(nameSearchVO);
        List<Topic> topicList = feedDao.pageTopic(nameSearchVO);

        // 分页数据
        PageInfo<Topic> pageInfo = new PageInfo<>();
        ParamUtil.setPageInfo(pageInfo, nameSearchVO, total, topicList);

        result.setData(pageInfo);
        return result;
    }

}