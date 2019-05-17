package me.happycao.lingxi.util;

import java.security.MessageDigest;

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
        String tokenTmp = DigestUtil.md5Hex(userId + BAR + password).toLowerCase();
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

    /**
     * md5
     */
    public static String md5Hex(String data) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(data.getBytes(CHARSET));
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * sha1
     */
    public static String sha1Hex(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(data.getBytes(CHARSET));
            byte[] digest = md.digest();

            StringBuilder builder = new StringBuilder();
            String shaHex;
            for (byte b : digest) {
                shaHex = Integer.toHexString(b & 0xFF);
                if (shaHex.length() < 2) {
                    builder.append(0);
                }
                builder.append(shaHex);
            }
            return builder.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
