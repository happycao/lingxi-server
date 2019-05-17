package me.happycao.lingxi.vo;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSearchUserId() {
        return searchUserId;
    }

    public void setSearchUserId(String searchUserId) {
        this.searchUserId = searchUserId;
    }

    @Override
    public String toString() {
        return "{" +
                "searchUserId='" + searchUserId + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
