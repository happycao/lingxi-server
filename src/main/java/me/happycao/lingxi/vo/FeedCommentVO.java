package me.happycao.lingxi.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/04/22
 * desc   : 动态评论分页
 * version: 1.0
 */
@Getter
@Setter
public class FeedCommentVO extends PageVO {

    private String feedId;

    @Override
    public String toString() {
        return super.toString();
    }

}