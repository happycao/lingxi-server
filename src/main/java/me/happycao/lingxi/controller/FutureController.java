package me.happycao.lingxi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.service.FutureService;
import me.happycao.lingxi.vo.FutureSaveVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/07/14
 * desc   : 写给未来
 * version: 1.0
 */
@Api(tags = "07-future", value = "FutureApi", description = "写给未来接口")
@RestController
@RequestMapping("/future")
public class FutureController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FutureService futureService;

    @ApiOperation(value = "写给未来", notes = "写给未来接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "X-App-Token", value = "token", required = true),
            @ApiImplicitParam(paramType="form", name = "type", value = "展示类型，0、app展示，1、mail发送", required = true),
            @ApiImplicitParam(paramType="form", name = "mail", value = "邮箱,type为1校验"),
            @ApiImplicitParam(paramType="form", name = "futureInfo", value = "内容", required = true),
            @ApiImplicitParam(paramType="form", name = "startNum", value = "展示区间开始值，如1个月", required = true),
            @ApiImplicitParam(paramType="form", name = "endNum", value = "展示区间结束值，如10个月")
    })
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result saveFuture(FutureSaveVO futureSaveVO, @ApiIgnore @RequestAttribute(name = "userId") String userId) {

        if (futureSaveVO == null) {
            return Result.paramIsNull();
        }

        logger.warn("param is : {}", futureSaveVO.toString());

        return futureService.saveFuture(futureSaveVO, userId);
    }

}
