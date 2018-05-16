package me.happycao.lingxi.controller;

import me.happycao.lingxi.entity.TUser;
import me.happycao.lingxi.model.Result;
import me.happycao.lingxi.service.UserService;
import me.happycao.lingxi.vo.IdVO;
import me.happycao.lingxi.vo.LoginVO;
import me.happycao.lingxi.vo.RegisterVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result register(RegisterVO registerVO) {
        if (registerVO == null) return Result.paramIsNull();

        logger.info("param is :" + registerVO.toString());

        return userService.register(registerVO);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(LoginVO loginVO) {
        if (loginVO == null) return Result.paramIsNull();

        logger.info("param is :" + loginVO.toString());

        return userService.login(loginVO);
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @ResponseBody
    public Result resetPassword(RegisterVO registerVO) {
        if (registerVO == null) return Result.paramIsNull();

        logger.info("param is :" + registerVO.toString());

        return userService.resetPassword(registerVO);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateUser(TUser tUser) {
        if (tUser == null) return Result.paramIsNull();

        logger.info("param is :" + tUser.toString());

        return userService.updateUser(tUser);
    }

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public Result getUser(IdVO idVO) {
        if (idVO == null) return Result.paramIsNull();

        String id = idVO.getId();
        if (StringUtils.isEmpty(id)) return Result.idIsNull();

        logger.info("param is :" + idVO.toString());

        return userService.getUser(id);
    }

    @RequestMapping(value = "/rc/list", method = RequestMethod.POST)
    @ResponseBody
    public Result listRcUser() {

        return userService.listRcUser();
    }



}
