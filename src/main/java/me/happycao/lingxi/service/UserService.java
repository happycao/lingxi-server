package me.happycao.lingxi.service;

import me.happycao.lingxi.entity.TUser;
import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.vo.LoginVO;
import me.happycao.lingxi.vo.RegisterVO;
import me.happycao.lingxi.vo.UserSearchVO;
import me.happycao.lingxi.vo.UserUpdateVO;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 用户相关
 * version: 1.0
 */
public interface UserService {

    Result register(RegisterVO registerVO);

    Result login(LoginVO loginVO);

    Result resetPassword(RegisterVO registerVO);

    Result updateUser(UserUpdateVO userUpdateVO, String userId);

    Result getUser(String id, String userId);

    Result listRcUser();

    Result searchUser(UserSearchVO userSearchVO);

    TUser userInfo(String id);
}
