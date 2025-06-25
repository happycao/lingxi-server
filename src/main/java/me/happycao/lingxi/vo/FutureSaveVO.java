package me.happycao.lingxi.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/07/16
 * desc   : 写给未来参数
 * version: 1.0
 */
@Getter
@Setter
public class FutureSaveVO extends BaseVO {

    private Integer type;
    private String mail;
    private String futureInfo;
    private Integer startNum;
    private Integer endNum;

    @Override
    public String toString() {
        return super.toString();
    }

}