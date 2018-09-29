package me.happycao.lingxi.service;

import me.happycao.lingxi.entity.TFeedAction;
import me.happycao.lingxi.result.Result;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 动态相关操作，0点赞1收藏
 * version: 1.0
 */
public interface FeedActionService {

    Result saveFeedAction(TFeedAction tFeedAction);

    Result removeFeedAction(TFeedAction tFeedAction);
}
