package me.happycao.lingxi.dao;

import me.happycao.lingxi.model.Comment;
import me.happycao.lingxi.vo.FeedCommentVO;
import me.happycao.lingxi.vo.StateVO;

import java.util.List;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 动态评论相关
 * version: 1.0
 */
public interface FeedCommentDao {

    Integer commentTotal(StateVO stateVO);

    List<Comment> pageFeedComment(FeedCommentVO feedCommentVO);
}
