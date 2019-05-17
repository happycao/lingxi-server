package me.happycao.lingxi.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间工具类
 *
 * @author happyc
 */
public class DateUtil {

    private static ThreadLocal<Map<String, DateFormat>> formatLocal = new ThreadLocal<>();
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTERN_YMD = "yyyy-MM-dd";

    /**
     * 获取pattern样式的DateFormat 线程安全
     */
    private static DateFormat getFormat(String pattern) {
        Map<String, DateFormat> formatMap = formatLocal.get();

        if (formatMap == null) {
            formatMap = new HashMap<>(1);
            formatLocal.set(formatMap);
        }

        return formatMap.computeIfAbsent(pattern, SimpleDateFormat::new);
    }

    /**
     * 格式化时间
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        return getFormat(pattern).format(date);
    }

    /**
     * 格式化时间
     */
    public static String format(Date date) {
        if (date == null) {
            return null;
        }
        return getFormat(PATTERN).format(date);
    }

    /**
     * 格式化时间
     */
    public static String formatYmd(Date date) {
        if (date == null) {
            return null;
        }
        return getFormat(PATTERN_YMD).format(date);
    }

    /**
     * 区间随机值
     */
    public static Integer nextNum(Integer min, Integer max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    /**
     * num天后
     */
    public static Date nextDay(Integer num) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, num);
        return calendar.getTime();
    }

    /**
     * num个月后
     */
    public static Date nextMonth(Integer num) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, num);
        return calendar.getTime();
    }

    /**
     * 获取相差天数，精度低
     */
    public static int getTermDays(Date start, Date end) {
        long times = start.getTime() - end.getTime();
        int day = 24 * 3600 * 1000;
        return (int) Math.abs(times / day);
    }
}
