package me.happycao.lingxi.dao;

import me.happycao.lingxi.model.Feed;
import me.happycao.lingxi.vo.FeedSearchVO;
import me.happycao.lingxi.vo.IdVO;

import java.util.List;

public interface FeedDao {

    Integer feedTotal(FeedSearchVO feedSearchVO);

    List<Feed> pageFeed(FeedSearchVO feedSearchVO);

    void viewFeed(IdVO idVO);
}