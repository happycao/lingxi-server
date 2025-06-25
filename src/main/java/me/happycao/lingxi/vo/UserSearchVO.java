package me.happycao.lingxi.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/05/29
 * desc   : 用户查询参数
 * version: 1.0
 */
@Getter
@Setter
public class UserSearchVO extends PageVO {

    private String username;

    @Override
    public String toString() {
        return super.toString();
    }

}