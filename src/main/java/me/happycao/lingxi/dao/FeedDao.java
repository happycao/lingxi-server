package me.happycao.lingxi.dao;

import me.happycao.lingxi.model.Feed;
import me.happycao.lingxi.vo.FeedSearchVO;
import me.happycao.lingxi.vo.IdVO;
import me.happycao.lingxi.vo.StateVO;

import java.util.List;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 动态相关
 * version: 1.0
 */
public interface FeedDao {

    Integer feedTotal(StateVO stateVO);

    List<Feed> pageFeed(FeedSearchVO feedSearchVO);

    void viewFeed(IdVO idVO);
}