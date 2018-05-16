package me.happycao.lingxi.util;

import me.happycao.lingxi.vo.PageVO;

import java.util.*;

/**
 * 参数相关工具类
 */
public class ParamUtil {

    /**
     * 获取uuid
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 分页参数为空
     */
    public static Boolean pageIsNull(PageVO pageVO) {
        return (pageVO.getPageNum() == null || pageVO.getPageSize() == null);
    }
}
