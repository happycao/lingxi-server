package me.happycao.lingxi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.service.FeedCommentService;
import me.happycao.lingxi.util.ParamUtil;
import me.happycao.lingxi.vo.FeedCommentSaveVO;
import me.happycao.lingxi.vo.FeedCommentVO;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 动态评论
 * version: 1.0
 */
@Slf4j
@Api(tags = "04-feed-comment", value = "FeedCommentApi", description = "动态评论接口")
@RestController
@RequestMapping("/feed/comment")
public class FeedCommentController {

    @Resource
    private FeedCommentService feedCommentService;

    @ApiOperation(value = "分页动态评论", notes = "分页动态评论接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "X-App-Token", value = "token", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页数", required = true, dataTypeClass = Integer.class, defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "页容量", required = true, dataTypeClass = Integer.class, defaultValue = "10"),
            @ApiImplicitParam(paramType = "query", name = "feedId", value = "动态id", dataTypeClass = String.class, required = true)
    })
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public Result pageFeedEvaluate(FeedCommentVO feedCommentVO) {

        if (feedCommentVO == null) {
            return Result.paramIsNull();
        }

        if (ParamUtil.pageIsNull(feedCommentVO)) {
            return Result.pageIsNull();
        }

        log.info("param is :" + feedCommentVO);

        return feedCommentService.pageFeedComment(feedCommentVO);
    }

    @ApiOperation(value = "动态评论回复", notes = "动态评论回复接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "X-App-Token", value = "token", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(paramType = "query", name = "type", value = "类型，0评论1回复", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(paramType = "query", name = "feedId", value = "动态id", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(paramType = "query", name = "commentId", value = "评论id", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(paramType = "query", name = "toUserId", value = "被评论|回复用户id", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(paramType = "query", name = "commentInfo", value = "评论|回复内容", dataTypeClass = String.class, required = true)
    })
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result saveFeedComment(@ApiIgnore FeedCommentSaveVO feedCommentSaveVO,
                                  @ApiIgnore @RequestAttribute(name = "userId") String userId) {

        if (feedCommentSaveVO == null) {
            return Result.paramIsNull();
        }

        log.info("param is :" + feedCommentSaveVO);

        return feedCommentService.saveFeedComment(feedCommentSaveVO, userId);
    }

    @ApiOperation(value = "未读评论数", notes = "未读评论数接口接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "X-App-Token", value = "token", dataTypeClass = String.class, required = true)
    })
    @RequestMapping(value = "/unread", method = RequestMethod.POST)
    @ResponseBody
    public Result unreadReply(@ApiIgnore @RequestAttribute(name = "userId") String userId) {

        log.info("param is :" + userId);

        return feedCommentService.unreadReply(userId);
    }

    @ApiOperation(value = "未读评论已读", notes = "未读已读接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "X-App-Token", value = "token", dataTypeClass = String.class, required = true)
    })
    @RequestMapping(value = "/unread/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateUnreadReply(@ApiIgnore @RequestAttribute(name = "userId") String userId) {

        log.info("param is :" + userId);

        return feedCommentService.updateUnreadReply(userId);
    }

}