package me.happycao.lingxi.vo;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/09/28
 * desc   : 用户信息修改参数
 * version: 1.0
 */
public class UserUpdateVO {

    private String username;

    private String password;

    private String phone;

    private Integer sex;

    private String qq;

    private String avatar;

    private String signature;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    @Override
    public String toString() {
        return "UserUpdateVO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                ", qq='" + qq + '\'' +
                ", avatar='" + avatar + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}