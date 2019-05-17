package me.happycao.lingxi.dao;

import me.happycao.lingxi.entity.TFeedAction;

import java.util.List;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 动态操作，包含点赞，收藏
 * version: 1.0
 */
public interface FeedActionDao {

    List<TFeedAction> listAll();
}
