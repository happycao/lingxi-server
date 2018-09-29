package me.happycao.lingxi.controller;

import me.happycao.lingxi.entity.TFeedComment;
import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.service.FeedCommentService;
import me.happycao.lingxi.util.ParamUtil;
import me.happycao.lingxi.vo.FeedCommentVO;
import me.happycao.lingxi.vo.UserIdVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 动态评论
 * version: 1.0
 */
@RestController
@RequestMapping("/feed/comment")
public class FeedCommentController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FeedCommentService feedCommentService;

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public Result pageFeedEvaluate(FeedCommentVO feedCommentVO) {

        if (feedCommentVO == null) {
            return Result.paramIsNull();
        }

        if (ParamUtil.pageIsNull(feedCommentVO)) {
            return Result.pageIsNull();
        }

        logger.info("param is :" + feedCommentVO.toString());

        return feedCommentService.pageFeedComment(feedCommentVO);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result saveFeedComment(TFeedComment tFeedComment) {

        if (tFeedComment == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + tFeedComment.toString());

        return feedCommentService.saveFeedComment(tFeedComment);
    }

    @RequestMapping(value = "/unread", method = RequestMethod.POST)
    @ResponseBody
    public Result unreadReply(UserIdVO userIdVO) {

        if (userIdVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + userIdVO.toString());

        return feedCommentService.unreadReply(userIdVO);
    }

    @RequestMapping(value = "/unread/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateUnreadReply(UserIdVO userIdVO) {

        if (userIdVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + userIdVO.toString());

        return feedCommentService.updateUnreadReply(userIdVO);
    }
}
