package me.happycao.lingxi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.happycao.lingxi.constant.Constant;
import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.service.UserService;
import me.happycao.lingxi.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author happyc
 */
@Api(tags = "01-user", value = "UserApi", description = "用户相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @ApiOperation(value = "账号注册", notes = "账号注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="form", name = "phone", value = "手机号", required = true),
            @ApiImplicitParam(paramType="form", name = "username", value = "用户名", required = true),
            @ApiImplicitParam(paramType="form", name = "password", value = "密码", required = true)
    })
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result register(RegisterVO registerVO) {
        if (registerVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + registerVO.toString());

        return userService.register(registerVO);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="form", name = "username", value = "用户名", required = true),
            @ApiImplicitParam(paramType="form", name = "password", value = "密码", required = true)
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(LoginVO loginVO) {
        if (loginVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + loginVO.toString());

        return userService.login(loginVO);
    }

    @ApiOperation(value = "密码重置", notes = "密码重置接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="form", name = "phone", value = "手机号", required = true),
            @ApiImplicitParam(paramType="form", name = "username", value = "用户名", required = true),
            @ApiImplicitParam(paramType="form", name = "password", value = "密码", required = true)
    })
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @ResponseBody
    public Result resetPassword(RegisterVO registerVO) {
        if (registerVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + registerVO.toString());

        return userService.resetPassword(registerVO);
    }

    @ApiOperation(value = "用户资料更改", notes = "用户资料更改接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "X-App-Token", value = "token", required = true),
            @ApiImplicitParam(paramType="form", name = "phone", value = "手机号"),
            @ApiImplicitParam(paramType="form", name = "username", value = "用户名"),
            @ApiImplicitParam(paramType="form", name = "password", value = "密码"),
            @ApiImplicitParam(paramType="form", name = "sex", value = "性别，-1未知，0女1男"),
            @ApiImplicitParam(paramType="form", name = "qq", value = "QQ号"),
            @ApiImplicitParam(paramType="form", name = "avatar", value = "头像路径"),
            @ApiImplicitParam(paramType="form", name = "signature", value = "个性签名")
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateUser(UserUpdateVO userUpdateVO, @ApiIgnore @RequestAttribute(name = "userId") String userId) {
        if (userUpdateVO == null) {
            return Result.paramIsNull();
        }

        logger.info("userId is {}, param is {}", userId, userUpdateVO.toString());

        return userService.updateUser(userUpdateVO, userId);
    }

    @ApiOperation(value = "用户信息查询", notes = "用户信息查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "X-App-Token", value = "token", required = true),
            @ApiImplicitParam(paramType="form", name = "id", value = "用户id", defaultValue = Constant.DEFAULT_USER_ID)
    })
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public Result getUser(IdVO idVO, @ApiIgnore @RequestAttribute(name = "userId") String userId) {
        String id = null;
        if (idVO != null) {
            id = idVO.getId();
        }

        logger.info("param is :" + id);

        return userService.getUser(id, userId);
    }

    @ApiIgnore
    @ApiOperation(value = "融云用户列表", notes = "融云用户列表接口")
    @RequestMapping(value = "/rc/list", method = RequestMethod.POST)
    @ResponseBody
    public Result listRcUser() {

        return userService.listRcUser();
    }

    @ApiOperation(value = "精准搜索用户", notes = "精准搜索用户接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "X-App-Token", value = "token", required = true),
            @ApiImplicitParam(paramType="form", name = "username", value = "用户名", required = true)
    })
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public Result searchUser(UserSearchVO userSearchVO) {
        if (userSearchVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + userSearchVO.toString());

        return userService.searchUser(userSearchVO);
    }

    @ApiOperation(value = "模糊查询用户", notes = "模糊查询用户接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "X-App-Token", value = "token", required = true),
            @ApiImplicitParam(paramType="form", name = "username", value = "用户名", required = true)
    })
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Result queryUser(UserSearchVO userSearchVO) {
        if (userSearchVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + userSearchVO.toString());

        return userService.queryUser(userSearchVO);
    }

}
