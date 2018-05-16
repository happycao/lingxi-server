package me.happycao.lingxi.vo;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/04/22
 * desc   : 动态评论
 * version: 1.0
 */
public class FeedCommentVO extends PageVO {

    private String feedId;

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    @Override
    public String toString() {
        return "FeedCommentVO{" +
                "pageNum=" + pageNum +
                ", feedId='" + feedId + '\'' +
                ", pageSize=" + pageSize +
                '}';
    }
}
