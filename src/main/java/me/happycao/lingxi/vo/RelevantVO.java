package me.happycao.lingxi.vo;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/04/22
 * desc   : 与我相关参数
 * version: 1.0
 */
public class RelevantVO extends PageVO {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RelevantVO{" +
                "pageNum=" + pageNum +
                ", userId='" + userId + '\'' +
                ", pageSize=" + pageSize +
                '}';
    }
}
