package me.happycao.lingxi.vo;

import springfox.documentation.annotations.ApiIgnore;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/03/02
 * desc   : 动态列表参数
 * version: 1.0
 */
public class FeedSearchVO extends PageVO {

    private String userId;
    private String searchUserId;
    private String topicId;

    @ApiIgnore
    public String getUserId() {
        return userId;
    }

    @ApiIgnore
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSearchUserId() {
        return searchUserId;
    }

    public void setSearchUserId(String searchUserId) {
        this.searchUserId = searchUserId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return "{" +
                "searchUserId='" + searchUserId + '\'' +
                ", topicId='" + topicId + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
