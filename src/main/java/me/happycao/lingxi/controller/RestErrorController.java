package me.happycao.lingxi.controller;

import me.happycao.lingxi.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author happyc
 * 错误返回处理
 */
@ApiIgnore
@RestController
public class RestErrorController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "400")
    @ResponseBody
    public Result error400() {
        logger.error("400");

        return Result.error("400", "400 Request Error");
    }

    @RequestMapping(value = "404")
    @ResponseBody
    public Result error404() {
        logger.error("404");

        return Result.error("404", "404 Not Found");
    }

    @RequestMapping(value = "500")
    @ResponseBody
    public Result error500() {
        logger.error("500");

        return Result.error("500", "500 Internal Server Error");
    }
}
