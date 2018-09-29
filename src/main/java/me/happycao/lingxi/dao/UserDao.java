package me.happycao.lingxi.dao;

import me.happycao.lingxi.model.User;

import java.util.List;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 用户相关
 * version: 1.0
 */
public interface UserDao {

    /**
     * 获取最新的uid
     */
    Integer getNewUid();

    List<User> listRcUser();
}