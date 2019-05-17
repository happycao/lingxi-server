package me.happycao.lingxi.vo;

import java.util.List;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/04/22
 * desc   : 动态保存参数
 * version: 1.0
 */
public class FeedSaveVO {

    private String userId;

    private String feedInfo;

    private List<String> photoList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFeedInfo() {
        return feedInfo;
    }

    public void setFeedInfo(String feedInfo) {
        this.feedInfo = feedInfo;
    }

    public List<String> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<String> photoList) {
        this.photoList = photoList;
    }

    @Override
    public String toString() {
        return "FeedSaveVO{" +
                "userId='" + userId + '\'' +
                ", feedInfo='" + feedInfo + '\'' +
                ", photoList=" + photoList +
                '}';
    }
}
