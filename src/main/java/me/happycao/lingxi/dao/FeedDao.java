package me.happycao.lingxi.dao;

import me.happycao.lingxi.model.Feed;
import me.happycao.lingxi.model.Topic;
import me.happycao.lingxi.vo.FeedSearchVO;
import me.happycao.lingxi.vo.IdVO;
import me.happycao.lingxi.vo.NameSearchVO;

import java.util.List;

public interface FeedDao {

    Integer feedTotal(FeedSearchVO feedSearchVO);

    List<Feed> pageFeed(FeedSearchVO feedSearchVO);

    void viewFeed(IdVO idVO);

    Integer topicTotal(NameSearchVO nameSearchVO);

    List<Topic> pageTopic(NameSearchVO nameSearchVO);
}