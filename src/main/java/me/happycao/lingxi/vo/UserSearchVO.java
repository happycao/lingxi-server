package me.happycao.lingxi.vo;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/05/29
 * desc   : 用户查询参数
 * version: 1.0
 */
public class UserSearchVO {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserSearchVO{" +
                "username='" + username + '\'' +
                '}';
    }
}
