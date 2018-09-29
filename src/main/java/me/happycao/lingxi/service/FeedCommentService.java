package me.happycao.lingxi.service;

import me.happycao.lingxi.entity.TFeedComment;
import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.vo.FeedCommentVO;
import me.happycao.lingxi.vo.UserIdVO;

/**
 * @author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 动态评论
 * version: 1.0
 */
public interface FeedCommentService {

    Result pageFeedComment(FeedCommentVO feedCommentVO);

    Result saveFeedComment(TFeedComment tFeedComment);

    Result unreadReply(UserIdVO userIdVO);

    Result updateUnreadReply(UserIdVO userIdVO);
}
