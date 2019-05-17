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
import me.happycao.lingxi.vo.FeedCommentSaveVO;
import me.happycao.lingxi.vo.FeedCommentVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 动态评论
 * version: 1.0
 */
@Service
public class FeedCommentServiceImpl implements FeedCommentService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private TFeedCommentMapper tFeedCommentMapper;

    @Resource
    private FeedCommentDao feedCommentDao;

    @Override
    public Result pageFeedComment(FeedCommentVO feedCommentVO) {
        Result result = Result.success();

        String feedId = feedCommentVO.getFeedId();
        if (StringUtils.isEmpty(feedId)) {
            logger.warn("param warn : feedId为空");
            result.setCodeAndMsg(Constant.ERROR_CODE_ID_NULL, "feedId为空");
            return result;
        }

        // 设置分页
        ParamUtil.setPage(feedCommentVO);

        Integer total = feedCommentDao.commentTotal();
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
     * 保存动态评论
     */
    @Override
    public Result saveFeedComment(FeedCommentSaveVO feedCommentSaveVO, String userId) {
        Result result = Result.success();
        // 参数校验
        Integer type = feedCommentSaveVO.getType();
        String feedId = feedCommentSaveVO.getFeedId();
        String toUserId = feedCommentSaveVO.getToUserId();
        String commentInfo = feedCommentSaveVO.getCommentInfo();
        if (type == null || StringUtils.isEmpty(feedId)
                || StringUtils.isEmpty(toUserId) || StringUtils.isEmpty(commentInfo)) {
            logger.warn("param warn : 必填参数不全");
            result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "必填参数不全");
            return result;
        }

        // 评论
        if (type == 0) {
            feedCommentSaveVO.setCommentId(null);
        }
        // 回复
        if (type == 1) {
            String commentId = feedCommentSaveVO.getCommentId();
            if (StringUtils.isEmpty(commentId)) {
                logger.warn("param warn : 回复commentId不能为空");
                result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "回复commentId不能为空");
                return result;
            }
        }

        // 数据转换
        TFeedComment tFeedComment = new TFeedComment();
        BeanUtils.copyProperties(feedCommentSaveVO, tFeedComment);
        String id = ParamUtil.getUUID();
        tFeedComment.setId(id);
        tFeedCommentMapper.insertSelective(tFeedComment);

        result.setData(tFeedCommentMapper.selectByPrimaryKey(id));
        return result;
    }

    @Override
    public Result unreadReply(String userId) {
        Result result = Result.success();

        Example example = new Example(TFeedComment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("toUserId", userId);
        criteria.andEqualTo("isLook", false);

        Integer count = tFeedCommentMapper.selectCountByExample(example);

        result.setData(count);
        return result;
    }

    @Override
    public Result updateUnreadReply(String userId) {
        Result result = Result.success();

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
