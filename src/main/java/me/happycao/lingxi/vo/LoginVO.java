package me.happycao.lingxi.vo;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/03/02
 * desc   : 登录参数
 * version: 1.0
 */
public class LoginVO {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginVO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
