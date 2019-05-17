package me.happycao.lingxi.util;

import me.happycao.lingxi.vo.PageVO;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参数相关工具类
 * @author happyc
 */
public class ParamUtil {

    /**
     * ##
     */
    private static final String TOPIC = "#[\\p{Print}\\p{InCJKUnifiedIdeographs}&&[^#]]+#";
    /**
     * @
     */
    private static final String AT = "@[\\w\\p{InCJKUnifiedIdeographs}-]{1,26}";
    /**
     * email
     */
    private static final String EMAIL = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

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
     * 设置分页
     */
    public static void setPage(PageVO pageVO) {
        Integer pageNum = pageVO.getPageNum();
        Integer pageSize = pageVO.getPageSize();
        pageNum = pageNum == null ? 1 : pageNum;
        pageNum = pageNum < 1 ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        pageVO.setPageNum(pageNum);
        pageVO.setPageSize(pageSize);
    }

    /**
     * 话题
     */
    public static List<String> getTopics(String feedInfo){
        List<String> topics = new ArrayList<>();
        Pattern p = Pattern.compile(TOPIC);
        Matcher m = p.matcher(feedInfo);
        while (m.find()) {
            String topic = m.group();
            topic = topic.replaceAll("#", "");
            topics.add(topic);
        }
        return topics;
    }

    /**
     * @
     */
    public static List<String> getAts(String feedInfo){
        List<String> ats = new ArrayList<>();
        Pattern p = Pattern.compile(AT);
        Matcher m = p.matcher(feedInfo);
        while (m.find()) {
            String at = m.group();
            at = at.replaceAll("@", "");
            ats.add(at);
        }
        return ats;
    }

    /**
     * 是否为邮箱
     */
    public static boolean isMail(String mail) {
        Pattern p = Pattern.compile(EMAIL);
        Matcher m = p.matcher(mail);
        return m.matches();
    }
}
