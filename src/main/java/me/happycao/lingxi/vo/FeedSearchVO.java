package me.happycao.lingxi.vo;

import lombok.Getter;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/03/02
 * desc   : 动态列表参数
 * version: 1.0
 */
@Getter
@Setter
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

    @Override
    public String toString() {
        return super.toString();
    }

}