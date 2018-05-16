package me.happycao.lingxi.controller;

import me.happycao.lingxi.entity.TFeedAction;
import me.happycao.lingxi.model.Result;
import me.happycao.lingxi.service.FeedActionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 动态相关操作
 * version: 1.0
 */
@RestController
@RequestMapping("/feed/action")
public class FeedActionController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FeedActionService feedActionService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result saveFeedAction(TFeedAction tFeedAction) {

        if (tFeedAction == null) return Result.paramIsNull();

        logger.info("param is :" + tFeedAction.toString());

        return feedActionService.saveFeedAction(tFeedAction);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public Result removeFeedAction(TFeedAction tFeedAction) {

        if (tFeedAction == null) return Result.paramIsNull();

        logger.info("param is :" + tFeedAction.toString());

        return feedActionService.removeFeedAction(tFeedAction);
    }
}
