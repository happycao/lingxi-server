package me.happycao.lingxi.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : happyc
 * e-mail  : bafs.jy@live.com
 * time    : 2018/09/29
 * desc    : 评论发布
 * version : 1.0
 */
@Getter
@Setter
public class FeedCommentSaveVO extends BaseVO {

    private Integer type;
    private String feedId;
    private String commentId;
    private String userId;
    private String toUserId;
    private String commentInfo;

    @Override
    public String toString() {
        return super.toString();
    }

}