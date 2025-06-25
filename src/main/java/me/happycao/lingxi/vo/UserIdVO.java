package me.happycao.lingxi.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/04/23
 * desc   : 用户id参数
 * version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class UserIdVO extends PageVO {

    private String userId;

    public UserIdVO(PageVO pageVO) {
        setPageNum(pageVO.getPageNum());
        setPageSize(pageVO.getPageSize());
    }

    public UserIdVO(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}