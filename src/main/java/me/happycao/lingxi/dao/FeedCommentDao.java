package me.happycao.lingxi.dao;

import me.happycao.lingxi.model.Comment;
import me.happycao.lingxi.vo.FeedCommentVO;

import java.util.List;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 动态评论
 * version: 1.0
 */
public interface FeedCommentDao {

    Integer commentTotal();

    List<Comment> pageFeedComment(FeedCommentVO feedCommentVO);
}
