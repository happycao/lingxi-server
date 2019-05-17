package me.happycao.lingxi.vo;

/**
 * @author : happyc
 * e-mail  : bafs.jy@live.com
 * time    : 2018/09/29
 * desc    : 评论发布
 * version : 1.0
 */
public class FeedCommentSaveVO {

    private Integer type;
    private String feedId;
    private String commentId;
    private String userId;
    private String toUserId;
    private String commentInfo;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

    @Override
    public String toString() {
        return "FeedCommentSaveVO{" +
                "type=" + type +
                ", feedId='" + feedId + '\'' +
                ", commentId='" + commentId + '\'' +
                ", userId='" + userId + '\'' +
                ", toUserId='" + toUserId + '\'' +
                ", commentInfo='" + commentInfo + '\'' +
                '}';
    }
}
