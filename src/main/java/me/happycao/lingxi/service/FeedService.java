package me.happycao.lingxi.service;

import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.vo.*;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 动态
 * version: 1.0
 */
public interface FeedService {

    Result pageFeed(FeedSearchVO feedSearchVO, String userId);

    Result saveFeed(FeedSaveVO feedSaveVO, String userId);

    Result viewFeed(IdVO idVO);

    Result pageRelevant(RelevantVO relevantVO, String userId);

    Result pageMineReply(RelevantVO relevantVO, String userId);

    Result removeFeed(IdVO idVO, String userId);

    Result queryTopic(NameSearchVO nameSearchVO);
}