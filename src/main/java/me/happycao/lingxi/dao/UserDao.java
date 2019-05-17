package me.happycao.lingxi.dao;

import me.happycao.lingxi.model.User;
import me.happycao.lingxi.vo.LoginVO;

import java.util.List;

public interface UserDao {

    User login(LoginVO loginVO);

    /** 获取最新的uid */
    Integer getNewUid();

    List<User> listRcUser();
}