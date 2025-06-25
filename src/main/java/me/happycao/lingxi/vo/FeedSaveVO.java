package me.happycao.lingxi.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/04/22
 * desc   : 动态保存参数
 * version: 1.0
 */
@Getter
@Setter
public class FeedSaveVO extends BaseVO {

    private String userId;

    private String feedInfo;

    private List<String> photoList;

    @Override
    public String toString() {
        return super.toString();
    }

}