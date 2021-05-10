package me.happycao.lingxi.dao;

import me.happycao.lingxi.model.Future;
import me.happycao.lingxi.vo.PageVO;
import me.happycao.lingxi.vo.UserIdVO;

import java.util.List;

/**
 * @author : happyc
 * e-mail  : bafs.jy@live.com
 * time    : 2018/10/02
 * desc    : 写给未来相关
 * version : 1.0
 */
public interface FutureDao {

    /**
     * 今天需要发送的消息
     */
    List<Future> listFutureToDay();

    Integer futureTotal(UserIdVO userIdVO);

    List<Future> pageFuture(UserIdVO userIdVO);
}
