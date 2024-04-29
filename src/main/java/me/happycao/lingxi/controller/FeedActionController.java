package me.happycao.lingxi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.service.FeedActionService;
import me.happycao.lingxi.vo.FeedActionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 动态相关操作
 * version: 1.0
 */
@Api(tags = "03-feed-action", value = "FeedActionApi", description = "动态相关操作接口")
@RestController
@RequestMapping("/feed/action")
public class FeedActionController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private FeedActionService feedActionService;

    @ApiOperation(value = "动态操作", notes = "动态喜欢|收藏接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "X-App-Token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", name = "type", value = "操作类型，0点赞1收藏", required = true),
            @ApiImplicitParam(paramType = "query", name = "feedId", value = "动态id", required = true)
    })
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result saveFeedAction(@ApiIgnore FeedActionVO feedActionVO,
                                 @ApiIgnore @RequestAttribute(name = "userId") String userId) {

        if (feedActionVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + feedActionVO.toString());

        return feedActionService.saveFeedAction(feedActionVO, userId);
    }

    @ApiIgnore
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public Result removeFeedAction(@ApiIgnore FeedActionVO feedActionVO,
                                   @ApiIgnore @RequestAttribute(name = "userId") String userId) {

        if (feedActionVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + feedActionVO.toString());

        return feedActionService.removeFeedAction(feedActionVO, userId);
    }
}
