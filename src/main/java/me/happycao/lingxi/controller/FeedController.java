package me.happycao.lingxi.controller;

import me.happycao.lingxi.model.Result;
import me.happycao.lingxi.service.FeedService;
import me.happycao.lingxi.util.ParamUtil;
import me.happycao.lingxi.vo.FeedSaveVO;
import me.happycao.lingxi.vo.FeedSearchVO;
import me.happycao.lingxi.vo.IdVO;
import me.happycao.lingxi.vo.RelevantVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 动态相关
 */
@RestController
@RequestMapping("/feed")
public class FeedController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FeedService feedService;

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public Result pageFeed(FeedSearchVO feedSearchVO) {

        if (feedSearchVO == null) return Result.paramIsNull();

        if (ParamUtil.pageIsNull(feedSearchVO)) return Result.pageIsNull();

        logger.info("param is :" + feedSearchVO.toString());

        return feedService.pageFeed(feedSearchVO);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result saveFeed(FeedSaveVO feedSaveVO) {

        if (feedSaveVO == null) return Result.paramIsNull();

        logger.info("param is :" + feedSaveVO.toString());

        return feedService.saveFeed(feedSaveVO);
    }

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    @ResponseBody
    public Result viewFeed(IdVO idVO) {

        if (idVO == null) return Result.paramIsNull();

        if (StringUtils.isEmpty(idVO.getId())) return Result.idIsNull();

        logger.info("param is :" + idVO.toString());

        return feedService.viewFeed(idVO);
    }

    @RequestMapping(value = "/relevant", method = RequestMethod.POST)
    @ResponseBody
    public Result pageRelevant(RelevantVO relevantVO) {

        if (relevantVO == null) return Result.paramIsNull();

        if (ParamUtil.pageIsNull(relevantVO)) return Result.pageIsNull();

        logger.info("param is :" + relevantVO.toString());

        return feedService.pageRelevant(relevantVO);
    }

    @RequestMapping(value = "/mine/reply", method = RequestMethod.POST)
    @ResponseBody
    public Result pageMineReply(RelevantVO relevantVO) {

        if (relevantVO == null) return Result.paramIsNull();

        if (ParamUtil.pageIsNull(relevantVO)) return Result.pageIsNull();

        logger.info("param is :" + relevantVO.toString());

        return feedService.pageMineReply(relevantVO);
    }
}