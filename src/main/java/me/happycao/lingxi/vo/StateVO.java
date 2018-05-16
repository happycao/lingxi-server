package me.happycao.lingxi.vo;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/04/22
 * desc   : 状态参数
 * version: 1.0
 */
public class StateVO {

    private Integer state;

    public StateVO() {
    }

    public StateVO(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "StateVO{" +
                "state=" + state +
                '}';
    }
}
