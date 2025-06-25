package me.happycao.lingxi.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/04/15
 * desc   : 注册参数
 * version: 1.0
 */
@Getter
@Setter
public class RegisterVO extends BaseVO {

    private String phone;
    private String username;
    private String password;

    @Override
    public String toString() {
        return super.toString();
    }

}