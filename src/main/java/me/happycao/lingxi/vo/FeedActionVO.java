package me.happycao.lingxi.vo;

public class FeedActionVO {

    private Integer type;

    private String feedId;

    private String userId;

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
        this.feedId = feedId == null ? null : feedId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    @Override
    public String toString() {
        return "FeedActionVO{" +
                "type=" + type +
                ", feedId='" + feedId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}