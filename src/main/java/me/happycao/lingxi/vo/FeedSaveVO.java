package me.happycao.lingxi.vo;

import me.happycao.lingxi.entity.TFeed;

import java.util.List;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/04/22
 * desc   : 动态保存参数
 * version: 1.0
 */
public class FeedSaveVO extends TFeed{

    private List<String> photoList;

    public List<String> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<String> photoList) {
        this.photoList = photoList;
    }

    @Override
    public String toString() {
        return "FeedSaveVO{" +
                super.toString() +
                ", photoList=" + photoList +
                '}';
    }
}
