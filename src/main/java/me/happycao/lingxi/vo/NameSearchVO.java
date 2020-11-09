package me.happycao.lingxi.vo;

/**
 * @author : happyc
 * time    : 2020/11/09
 * desc    :
 * version : 1.0
 */
public class NameSearchVO extends PageVO {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
