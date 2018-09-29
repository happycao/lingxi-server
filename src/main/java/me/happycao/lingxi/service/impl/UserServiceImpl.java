package me.happycao.lingxi.service.impl;

import me.happycao.lingxi.entity.TUser;
import me.happycao.lingxi.mapper.TUserMapper;
import me.happycao.lingxi.dao.UserDao;
import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.service.UserService;
import me.happycao.lingxi.util.ParamUtil;
import me.happycao.lingxi.vo.LoginVO;
import me.happycao.lingxi.vo.RegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 用户相关
 * version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TUserMapper tUserMapper;

    @Autowired
    private UserDao userDao;

    @Override
    public Result register(RegisterVO registerVO) {
        Result result = Result.success();

        String username = registerVO.getUsername();
        String phone = registerVO.getPhone();
        String password = registerVO.getPassword();

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            result.setCodeAndMsg("00103", "用户名或手机号或密码为空");
            return result;
        }

        // 检测手机号
        TUser param = new TUser();
        param.setPhone(phone);
        TUser tUser = tUserMapper.selectOne(param);
        if (tUser != null) {
            result.setCodeAndMsg("00105", "手机号" + phone + "已注册");
            return result;
        }
        // 检用户名
        param.setPhone(null);
        param.setUsername(username);
        tUser = tUserMapper.selectOne(param);
        if (tUser != null) {
            result.setCodeAndMsg("00106", "用户名" + username + "已存在");
            return result;
        }

        tUser = new TUser();
        tUser.setId(ParamUtil.getUUID());
        tUser.setUid(userDao.getNewUid());
        tUser.setUsername(username);
        // 密码加密暂不支持
        tUser.setPassword(password);
        tUser.setPhone(registerVO.getPhone());
        tUserMapper.insertSelective(tUser);

        // 因为数据隐蔽性，返回时在如password字段get方法上注解@JsonIgnore
        // 该字段不会被序列化和反序列化
        // 或者类上注解@JsonIgnoreProperties({"password"})
        result.setData(tUser);
        return result;
    }

    @Override
    public Result login(LoginVO loginVO) {
        Result result = Result.success();

        String username = loginVO.getUsername();
        String password = loginVO.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            result.setCodeAndMsg("00103", "用户名或密码为空");
            return result;
        }

        // 用户名登录
        TUser param = new TUser();
        param.setUsername(username);
        param.setPassword(password);
        TUser tUser = tUserMapper.selectOne(param);
        // 支持手机号登录
        if (tUser == null) {
            param.setUsername(null);
            param.setPhone(username);
            tUser = tUserMapper.selectOne(param);
        }
        if (tUser == null) {
            result.setCodeAndMsg("00104", "用户名或密码错误");
            return result;
        }

        // 更新登录时间
        tUser.setLoginTime(new Date());
        tUserMapper.updateByPrimaryKeySelective(tUser);

        result.setData(tUser);
        return result;
    }

    @Override
    public Result resetPassword(RegisterVO registerVO) {
        Result result = Result.success();

        String username = registerVO.getUsername();
        String phone = registerVO.getPhone();
        String password = registerVO.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            result.setCodeAndMsg("00103", "用户名或手机号或密码为空");
            return result;
        }

        //检查用户存不存在
        TUser param = new TUser();
        param.setUsername(username);
        param.setPhone(phone);
        TUser tUser = tUserMapper.selectOne(param);
        if (tUser == null) {
            result.setCodeAndMsg("00104", "用户不存在");
            return result;
        }
        tUser.setPassword(password);
        tUserMapper.updateByPrimaryKeySelective(tUser);

        result.setData(tUser);
        return result;
    }

    @Override
    public Result updateUser(TUser tUser) {
        Result result = Result.success();

        String id = tUser.getId();
        if (StringUtils.isEmpty(id)) {
            result.setCodeAndMsg("00103", "id为空");
            return result;
        }

        TUser param = new TUser();
        TUser temp;
        // 检测手机号
        String phone = tUser.getPhone();
        if (!StringUtils.isEmpty(phone)) {
            param.setPhone(phone);
            temp = tUserMapper.selectOne(param);
            if (temp != null) {
                result.setCodeAndMsg("00105", "手机号" + phone + "已存在");
                return result;
            }
        }
        // 检用户名
        String username = tUser.getUsername();
        if (!StringUtils.isEmpty(phone)) {
            param.setPhone(null);
            param.setUsername(username);
            temp = tUserMapper.selectOne(param);
            if (temp != null) {
                result.setCodeAndMsg("00106", "用户名" + username + "已存在");
                return result;
            }
        }

        tUser.setUid(null);
        tUser.setImToken(null);
        tUserMapper.updateByPrimaryKeySelective(tUser);

        temp = tUserMapper.selectByPrimaryKey(id);
        result.setData(temp);
        return result;
    }

    @Override
    public Result getUser(String id) {
        return Result.success(tUserMapper.selectByPrimaryKey(id));
    }

    @Override
    public Result listRcUser() {
        return Result.success(userDao.listRcUser());
    }

}
