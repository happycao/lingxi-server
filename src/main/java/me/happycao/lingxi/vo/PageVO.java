package me.happycao.lingxi.vo;
/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/03/02
 * desc   : 分页参数
 * version: 1.0
 */
public class PageVO {

    protected Integer pageNum;
    protected Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
