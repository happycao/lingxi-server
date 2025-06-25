package me.happycao.lingxi.controller;

import lombok.extern.slf4j.Slf4j;
import me.happycao.lingxi.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author happyc
 * 错误返回处理
 */
@Slf4j
@ApiIgnore
@RestController
public class RestErrorController {

    @RequestMapping(value = "400")
    @ResponseBody
    public Result error400() {
        log.error("400");

        return Result.error("400", "400 Request Error");
    }

    @RequestMapping(value = "404")
    @ResponseBody
    public Result error404() {
        log.error("404");

        return Result.error("404", "404 Not Found");
    }

    @RequestMapping(value = "500")
    @ResponseBody
    public Result error500() {
        log.error("500");

        return Result.error("500", "500 Internal Server Error");
    }

}