package me.happycao.lingxi.util;

import me.happycao.lingxi.vo.PageVO;

import java.util.UUID;

/**
 * @author Bafs
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

    /**
     * 分页管理
     */
    public static void setPage(PageVO pageVO){
        Integer pageNum = pageVO.getPageNum();
        Integer pageSize = pageVO.getPageSize();
        pageNum = pageNum == null ? 1 : pageNum;
        pageNum = pageNum < 1 ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        pageVO.setPageNum(pageNum);
        pageVO.setPageSize(pageSize);
    }
}
