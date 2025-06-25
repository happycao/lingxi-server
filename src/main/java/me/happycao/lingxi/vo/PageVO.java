package me.happycao.lingxi.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/03/02
 * desc   : 分页参数
 * version: 1.0
 */
@Getter
@Setter
public class PageVO extends BaseVO{

    protected Integer pageNum;
    protected Integer pageSize;

    @Override
    public String toString() {
        return super.toString();
    }

}