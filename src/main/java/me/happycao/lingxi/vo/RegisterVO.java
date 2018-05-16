package me.happycao.lingxi.vo;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/04/15
 * desc   : 注册参数
 * version: 1.0
 */
public class RegisterVO {

    private String phone;
    private String username;
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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
        return "RegisterVO{" +
                "phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
