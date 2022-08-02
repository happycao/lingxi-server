package me.happycao.lingxi.service.impl;

import me.happycao.lingxi.dao.UserDao;
import me.happycao.lingxi.entity.TUser;
import me.happycao.lingxi.mapper.TUserMapper;
import me.happycao.lingxi.model.PageInfo;
import me.happycao.lingxi.model.User;
import me.happycao.lingxi.model.UserToken;
import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.service.UserService;
import me.happycao.lingxi.util.DigestUtil;
import me.happycao.lingxi.util.ParamUtil;
import me.happycao.lingxi.vo.LoginVO;
import me.happycao.lingxi.vo.RegisterVO;
import me.happycao.lingxi.vo.UserSearchVO;
import me.happycao.lingxi.vo.UserUpdateVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 用户相关
 * version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private TUserMapper tUserMapper;

    @Resource
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

        // 隐藏手机号
        tUser.setPhone(DigestUtil.markPhone(tUser.getPhone()));
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
        if (tUser.getState() != 1) {
            result.setCodeAndMsg("00105", "该用户已停用");
            return result;
        }

        // 更新登录时间
        tUser.setLoginTime(new Date());
        tUserMapper.updateByPrimaryKeySelective(tUser);

        // 生成token
        UserToken userToken = new UserToken();
        BeanUtils.copyProperties(tUser, userToken);
        String token = DigestUtil.generatedToken(tUser.getId(), tUser.getPassword());
        userToken.setToken(token);

        result.setData(userToken);
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

        // 检查用户存不存在
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

        // 隐藏手机号
        tUser.setPhone(DigestUtil.markPhone(tUser.getPhone()));

        result.setData(tUser);
        return result;
    }

    @Override
    public Result updateUser(UserUpdateVO userUpdateVO, String userId) {
        Result result = Result.success();

        String phone = userUpdateVO.getPhone();
        String username = userUpdateVO.getUsername();
        String password = userUpdateVO.getPassword();
        String qq = userUpdateVO.getQq();
        String avatar = userUpdateVO.getAvatar();
        Integer sex = userUpdateVO.getSex();
        String signature = userUpdateVO.getSignature();
        if (StringUtils.isEmpty(phone) && StringUtils.isEmpty(username) && StringUtils.isEmpty(password) &&
                sex == null && StringUtils.isEmpty(qq) && StringUtils.isEmpty(avatar) &&
                StringUtils.isEmpty(signature)) {
            result.setCodeAndMsg("00104", "选填参数为空");
            return result;
        }

        TUser param = new TUser();
        TUser temp;
        // 检测手机号
        if (!StringUtils.isEmpty(phone)) {
            param.setPhone(phone);
            temp = tUserMapper.selectOne(param);
            if (temp != null) {
                result.setCodeAndMsg("00105", "手机号" + phone + "已绑定");
                return result;
            }
        }
        // 检用户名
        if (!StringUtils.isEmpty(username)) {
            param.setPhone(null);
            param.setUsername(username);
            temp = tUserMapper.selectOne(param);
            if (temp != null) {
                result.setCodeAndMsg("00106", "用户名" + username + "已存在");
                return result;
            }
        }

        // 切换数据
        BeanUtils.copyProperties(userUpdateVO, param);
        param.setId(userId);
        tUserMapper.updateByPrimaryKeySelective(param);

        temp = tUserMapper.selectByPrimaryKey(userId);

        // 隐藏手机号
        temp.setPhone(DigestUtil.markPhone(temp.getPhone()));

        result.setData(temp);
        return result;
    }

    @Override
    public Result getUser(String id, String userId) {
        Result result = Result.success();

        if (StringUtils.isEmpty(id)) {
            id = userId;
        }

        TUser tUser = tUserMapper.selectByPrimaryKey(id);
        if (tUser == null) {
            result.setCodeAndMsg("00102", "没有该用户");
            return result;
        }

        // 隐藏手机号
        tUser.setPhone(DigestUtil.markPhone(tUser.getPhone()));

        result.setData(tUser);
        return result;
    }

    @Override
    public Result listRcUser() {
        return Result.success(userDao.listRcUser());
    }

    @Override
    public Result searchUser(UserSearchVO userSearchVO) {
        Result result = Result.success();

        TUser tUser = null;

        String username = userSearchVO.getUsername();
        if (!StringUtils.isEmpty(username)) {
            TUser param = new TUser();
            param.setUsername(username);
            tUser = tUserMapper.selectOne(param);
        }

        if (tUser == null) {
            result.setCodeAndMsg("00102", "没有该用户");
            return result;
        }

        // 隐藏手机号
        tUser.setPhone(DigestUtil.markPhone(tUser.getPhone()));

        result.setData(tUser);
        return result;
    }

    @Override
    public TUser userInfo(String id) {
        return tUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public Result queryUser(UserSearchVO userSearchVO) {
        Result result = Result.success();

        ParamUtil.setPage(userSearchVO);

        Integer total = userDao.userTotal(userSearchVO);
        List<User> userList = userDao.pageUser(userSearchVO);

        // 分页数据
        PageInfo<User> pageInfo = new PageInfo<>();
        ParamUtil.setPageInfo(pageInfo, userSearchVO, total, userList);

        result.setData(pageInfo);
        return result;
    }

}
