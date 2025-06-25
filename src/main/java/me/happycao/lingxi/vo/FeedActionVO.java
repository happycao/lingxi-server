package me.happycao.lingxi.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedActionVO extends BaseVO{

    private Integer type;

    private String feedId;

    private String userId;

    @Override
    public String toString() {
        return super.toString();
    }

}