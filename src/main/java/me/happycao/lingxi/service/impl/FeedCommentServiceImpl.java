package me.happycao.lingxi.service.impl;

import me.happycao.lingxi.constant.Constant;
import me.happycao.lingxi.dao.FeedCommentDao;
import me.happycao.lingxi.entity.TFeedComment;
import me.happycao.lingxi.mapper.TFeedCommentMapper;
import me.happycao.lingxi.model.Comment;
import me.happycao.lingxi.model.PageInfo;
import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.service.FeedCommentService;
import me.happycao.lingxi.util.ParamUtil;
import me.happycao.lingxi.vo.FeedCommentVO;
import me.happycao.lingxi.vo.StateVO;
import me.happycao.lingxi.vo.UserIdVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 动态评论
 * version: 1.0
 */
@Service
public class FeedCommentServiceImpl implements FeedCommentService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TFeedCommentMapper tFeedCommentMapper;

    @Autowired
    private FeedCommentDao feedCommentDao;

    /**
     * 查询评论分页
     */
    @Override
    public Result pageFeedComment(FeedCommentVO feedCommentVO) {
        Result result = Result.success();

        String feedId = feedCommentVO.getFeedId();
        if (StringUtils.isEmpty(feedId)) {
            logger.warn("param warn : feedId为空");
            result.setCodeAndMsg(Constant.ERROR_CODE_ID_NULL, "feedId为空");
            return result;
        }

        // 分页设置
        ParamUtil.setPage(feedCommentVO);

        Integer total = feedCommentDao.commentTotal(new StateVO(1));
        List<Comment> commentList = feedCommentDao.pageFeedComment(feedCommentVO);

        // 分页数据
        PageInfo<Comment> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(feedCommentVO.getPageNum());
        pageInfo.setPageSize(feedCommentVO.getPageSize());
        pageInfo.setTotal(total);
        pageInfo.setList(commentList);
        pageInfo.setSize(commentList == null ? 0 : commentList.size());

        result.setData(pageInfo);
        return result;
    }

    /**
     * 提交评论
     */
    @Override
    public Result saveFeedComment(TFeedComment tFeedComment) {
        Result result = Result.success();

        Integer type = tFeedComment.getType();
        String feedId = tFeedComment.getFeedId();
        String userId = tFeedComment.getUserId();
        String toUserId = tFeedComment.getToUserId();
        String commentInfo = tFeedComment.getCommentInfo();
        if (type == null || StringUtils.isEmpty(feedId) || StringUtils.isEmpty(userId)
                || StringUtils.isEmpty(toUserId) || StringUtils.isEmpty(commentInfo)) {
            logger.warn("param warn : 必填参数不全");
            result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "必填参数不全");
            return result;
        }

        // 评论
        if (type == 0) {
            tFeedComment.setCommentId(null);
        }
        // 回复
        if (type == 1) {
            String commentId = tFeedComment.getCommentId();
            if (StringUtils.isEmpty(commentId)) {
                logger.warn("param warn : 回复commentId不能为空");
                result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "回复commentId不能为空");
                return result;
            }
        }

        String id = ParamUtil.getUUID();
        tFeedComment.setId(id);
        tFeedCommentMapper.insertSelective(tFeedComment);

        result.setData(tFeedCommentMapper.selectByPrimaryKey(id));
        return result;
    }

    /**
     * 未读评论
     */
    @Override
    public Result unreadReply(UserIdVO userIdVO) {
        Result result = Result.success();

        String userId = userIdVO.getUserId();
        if (StringUtils.isEmpty(userId)) {
            logger.warn("param warn : userId为空");
            result.setCodeAndMsg(Constant.ERROR_CODE_ID_NULL, "userId为空");
            return result;
        }

        Example example = new Example(TFeedComment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("toUserId", userId);
        criteria.andEqualTo("isLook", false);

        Integer count = tFeedCommentMapper.selectCountByExample(example);

        result.setData(count);
        return result;
    }

    /**
     * 评论标记已读
     */
    @Override
    public Result updateUnreadReply(UserIdVO userIdVO) {
        Result result = Result.success();

        String userId = userIdVO.getUserId();
        if (StringUtils.isEmpty(userId)) {
            logger.warn("param warn : userId为空");
            result.setCodeAndMsg(Constant.ERROR_CODE_ID_NULL, "userId为空");
            return result;
        }

        TFeedComment param = new TFeedComment();
        param.setIsLook(true);

        Example example = new Example(TFeedComment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("toUserId", userId);
        criteria.andEqualTo("isLook", false);

        Integer state = tFeedCommentMapper.updateByExampleSelective(param, example);

        result.setData(state);
        return result;
    }
}
