package me.happycao.lingxi.service;

import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.vo.FeedCommentSaveVO;
import me.happycao.lingxi.vo.FeedCommentVO;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 动态评论
 * version: 1.0
 */
public interface FeedCommentService {

    Result pageFeedComment(FeedCommentVO feedCommentVO);

    Result saveFeedComment(FeedCommentSaveVO feedCommentSaveVO, String userId);

    Result unreadReply(String userId);

    Result updateUnreadReply(String userId);
}
