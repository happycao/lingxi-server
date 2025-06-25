package me.happycao.lingxi.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/03/02
 * desc   : 登录参数
 * version: 1.0
 */
@Getter
@Setter
public class LoginVO extends BaseVO {

    private String username;
    private String password;

    @Override
    public String toString() {
        return super.toString();
    }

}