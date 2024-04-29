package me.happycao.lingxi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.service.FeedService;
import me.happycao.lingxi.util.ParamUtil;
import me.happycao.lingxi.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * @author happyc
 * 动态相关
 */
@Api(tags = "02-feed", value = "FeedApi", description = "动态相关接口")
@RestController
@RequestMapping("/feed")
public class FeedController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private FeedService feedService;

    /**
     * 动态分页
     */
    @ApiOperation(value = "分页动态", notes = "分页动态接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "X-App-Token", value = "token", required = true),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "页数", required = true, defaultValue = "1"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "页容量", required = true, defaultValue = "10"),
            @ApiImplicitParam(paramType="query", name = "searchUserId", value = "查询用户id"),
            @ApiImplicitParam(paramType="query", name = "topicId", value = "话题id")
    })
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public Result pageFeed(@ApiIgnore FeedSearchVO feedSearchVO,
                           @ApiIgnore @RequestAttribute(name = "userId") String userId) {

        if (feedSearchVO == null) {
            return Result.paramIsNull();
        }

        if (ParamUtil.pageIsNull(feedSearchVO)) {
            return Result.pageIsNull();
        }

        logger.info("param is :" + feedSearchVO.toString());

        return feedService.pageFeed(feedSearchVO, userId);
    }

    /**
     * 动态保存
     */
    @ApiOperation(value = "发布动态", notes = "发布动态接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "X-App-Token", value = "token", required = true),
            @ApiImplicitParam(paramType="query", name = "feedInfo", value = "动态内容", required = true),
            @ApiImplicitParam(paramType="query", name = "photoList", value = "图片列表 - 测试建议忽略")
    })
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result saveFeed(@ApiIgnore FeedSaveVO feedSaveVO,
                           @ApiIgnore @RequestAttribute(name = "userId") String userId) {

        if (feedSaveVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + feedSaveVO.toString());

        return feedService.saveFeed(feedSaveVO, userId);
    }

    /**
     * 动态查看
     */
    @ApiOperation(value = "动态查看数+1", notes = "动态查看数+1接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "X-App-Token", value = "token", required = true),
            @ApiImplicitParam(paramType="query", name = "id", value = "动态id", required = true)
    })
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    @ResponseBody
    public Result viewFeed(IdVO idVO) {

        if (idVO == null) {
            return Result.paramIsNull();
        }

        if (StringUtils.isEmpty(idVO.getId())) {
            return Result.idIsNull();
        }

        logger.info("param is :" + idVO.toString());

        return feedService.viewFeed(idVO);
    }

    /**
     * 与我相关
     */
    @ApiOperation(value = "与我相关", notes = "与我相关接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "X-App-Token", value = "token", required = true),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "页数", required = true, defaultValue = "1"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "页容量", required = true, defaultValue = "10")
    })
    @RequestMapping(value = "/relevant", method = RequestMethod.POST)
    @ResponseBody
    public Result pageRelevant(@ApiIgnore RelevantVO relevantVO,
                               @ApiIgnore @RequestAttribute(name = "userId") String userId) {

        if (relevantVO == null) {
            return Result.paramIsNull();
        }

        if (ParamUtil.pageIsNull(relevantVO)) {
            return Result.pageIsNull();
        }

        logger.info("param is :" + relevantVO.toString());

        return feedService.pageRelevant(relevantVO, userId);
    }

    /**
     * 我的回复
     */
    @ApiOperation(value = "我的回复", notes = "我的回复接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "X-App-Token", value = "token", required = true),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "页数", required = true, defaultValue = "1"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "页容量", required = true, defaultValue = "10")
    })
    @RequestMapping(value = "/mine/reply", method = RequestMethod.POST)
    @ResponseBody
    public Result pageMineReply(@ApiIgnore RelevantVO relevantVO,
                                @ApiIgnore @RequestAttribute(name = "userId") String userId) {

        if (relevantVO == null) {
            return Result.paramIsNull();
        }

        if (ParamUtil.pageIsNull(relevantVO)) {
            return Result.pageIsNull();
        }

        logger.info("param is :" + relevantVO.toString());

        return feedService.pageMineReply(relevantVO, userId);
    }

    /**
     * 动态删除
     */
    @ApiOperation(value = "删除动态", notes = "删除动态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "X-App-Token", value = "token", required = true),
            @ApiImplicitParam(paramType="query", name = "id", value = "动态id", required = true)
    })
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public Result removeFeed(IdVO idVO,
                             @ApiIgnore @RequestAttribute(name = "userId") String userId) {

        if (idVO == null) {
            return Result.paramIsNull();
        }

        if (StringUtils.isEmpty(idVO.getId())) {
            return Result.idIsNull();
        }

        logger.info("param is :" + idVO.toString());

        return feedService.removeFeed(idVO, userId);
    }

    /**
     * 话题查询
     */
    @ApiOperation(value = "模糊话题查询", notes = "模糊查询话题接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "X-App-Token", value = "token", required = true),
            @ApiImplicitParam(paramType="query", name = "name", value = "话题名", required = true)
    })
    @RequestMapping(value = "/topic/query", method = RequestMethod.POST)
    @ResponseBody
    public Result queryTopic(NameSearchVO nameSearchVO) {
        if (nameSearchVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + nameSearchVO.toString());

        return feedService.queryTopic(nameSearchVO);
    }
}