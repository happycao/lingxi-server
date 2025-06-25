package me.happycao.lingxi.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/09/28
 * desc   : 用户信息修改参数
 * version: 1.0
 */
@Getter
@Setter
public class UserUpdateVO extends BaseVO {

    private String username;

    private String password;

    private String phone;

    private Integer sex;

    private String qq;

    private String avatar;

    private String signature;

    @Override
    public String toString() {
        return super.toString();
    }

}