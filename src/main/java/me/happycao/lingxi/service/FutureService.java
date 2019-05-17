package me.happycao.lingxi.service;

import me.happycao.lingxi.entity.TFuture;
import me.happycao.lingxi.model.Future;
import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.vo.FutureSaveVO;

import java.util.List;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/07/15
 * desc   : 给未来的消息
 * version: 1.0
 */
public interface FutureService {

    Result saveFuture(FutureSaveVO futureSaveVO, String userId);

    List<Future> listFutureToDay();

    void sendFuture(Future future);

    void updateFutureList(List<TFuture> tFutureList);
}
