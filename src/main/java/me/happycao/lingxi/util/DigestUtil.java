package me.happycao.lingxi.util;

import org.springframework.util.DigestUtils;

/**
 * @author happyc
 * 加密工具类
 */
public class DigestUtil {

    private static final String CHARSET = "UTF-8";
    private static final String BAR = "-";
    public static final String AT = "@";
    public static final int TOKEN_ELEMENTS_NUM = 2;

    /**
     * 生成token，简单加密作为示例
     */
    public static String generatedToken(String userId, String password) {
        String str = userId + BAR + password;
        String tokenTmp = DigestUtils.md5DigestAsHex(str.getBytes()).toLowerCase();
        return userId + AT + tokenTmp;
    }

    /**
     * 隐藏手机
     */
    public static String markPhone(String phone) {
        if (phone == null) {
            return "";
        }
        int phoneLength = 11;
        if (phone.length() == phoneLength) {
            return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
        } else {
            return "";
        }
    }

}
