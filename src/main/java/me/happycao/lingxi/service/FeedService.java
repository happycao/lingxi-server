package me.happycao.lingxi.service;

import me.happycao.lingxi.model.Result;
import me.happycao.lingxi.vo.FeedSaveVO;
import me.happycao.lingxi.vo.FeedSearchVO;
import me.happycao.lingxi.vo.IdVO;
import me.happycao.lingxi.vo.RelevantVO;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 动态
 * version: 1.0
 */
public interface FeedService {

    Result pageFeed(FeedSearchVO feedSearchVO);

    Result saveFeed(FeedSaveVO feedSaveVO);

    Result viewFeed(IdVO idVO);

    Result pageRelevant(RelevantVO relevantVO);

    Result pageMineReply(RelevantVO relevantVO);
}