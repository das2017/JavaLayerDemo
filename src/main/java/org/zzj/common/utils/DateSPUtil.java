package org.zzj.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateSPUtil {

    /* 日期格式 */
    public final static String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public final static String YMDHM = "yyyy-MM-dd HH:mm";
    public final static String YMD = "yyyy-MM-dd";
    public final static String YM = "yyyy-MM";
    public final static String HMS = "HH:mm:ss";
    ;
    /* Calendar代表单位 */
    public final static int MILLISECOND = 14;
    public final static int SECOND = 13;
    public final static int MINUTE = 12;
    public final static int HOUR = 10;
    public final static int DAY = 5;
    public final static int MONTH = 2;

    /**
     * String转Date
     */
    public static Date parseYMD(String date) throws ParseException {
        return parse(date, YMD);
    }

    /**
     * String转Date
     */
    public static Date parse(String dateStr) throws ParseException {
        return parse(dateStr, YMDHMS);
    }

    /**
     * @param dateStr
     * @param format
     * @return Date
     * @throws ParseException
     */
    public static Date parse(String dateStr, String format) throws ParseException {
        if (dateStr == null || "".equals(dateStr.trim())) return null;
        if (null == format || "".equals(format)) format = YMDHMS;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = sdf.parse(dateStr);
        sdf = null;
        return date;
    }

    /**
     * 判断两个日期是否同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameDay(cal1, cal2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    /**
     * 判断两个日期是否同一天
     *
     * @param cal1
     * @param cal2
     * @return
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 != null && cal2 != null) {
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }


    /**
     * Date转String
     */
    public static String formatYMD(Date date) throws ParseException {
        return format(date, YMD);
    }

    /**
     * Date转String(默认格式)
     */
    public static String format(Date date) {
        try {
            return format(date, YMDHMS);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @param dateStr
     * @param format
     * @return String
     * @throws ParseException
     */
    public static String format(Date date, String format) throws ParseException {
        if (date == null) return null;
        if (null == format || "".equals(format)) format = YMDHMS;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateStr = sdf.format(date);
        sdf = null;
        return dateStr;
    }

    /**
     * 时间 加上（单位）
     *
     * @param date 时间
     * @param unit 单位
     * @return 日期加法之后的毫秒数
     * @author 王敏
     */
    public static Date calendarCom(Date date, int unit, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(unit, num); // 日期加上 时间 单位数量
        return calendar.getTime(); //返回毫秒数
    }

    /**
     * 日期加一天
     *
     * @param DateStr
     * @return yyyy-MM-dd
     * @throws ParseException
     * @date 2017年7月14日 下午8:50:59
     * @author wangm
     */
    public static String addOneDay(String DateStr) throws ParseException {
        return format(calendarCom(parse(DateStr, YMD), DAY, 1), YMD);
    }

    /**
     * 日期加天数
     *
     * @param date
     * @param day
     * @return
     * @throws ParseException
     * @date 2017年8月31日 下午4:23:46
     * @author wangm
     */
    public static Date addDay(Date date, Integer num) {
        return calendarCom(date, DAY, num);
    }

    /**
     * 日期加小时
     *
     * @param date
     * @param num
     * @return
     * @throws ParseException
     * @date 2017年10月10日 下午4:22:45
     * @author wangm
     */
    public static Date addHour(Date date, Integer num) throws ParseException {
        return calendarCom(date, HOUR, num);
    }

    public static Date addSecond(Date date, Integer num) throws ParseException {
        return calendarCom(date, SECOND, num);
    }


    public static void main(String[] args) {
        try {
//            System.out.println(new Date());
//            System.out.println(addSecond(new Date(), -1));
//            System.out.println(DateUtil.daysOfTwo(parse("2020-10-23 01:00:00"), parse("2020-10-23 24:00:00")));
            System.out.println(findDates(DateSPUtil.addDay(parse("2020-10-23 00:00:00"), 0), DateSPUtil.addDay(parse("2020-10-23 00:00:00"), 0)));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 判断星期
     *
     * @param date 日期
     * @return int 星期
     * @author 王敏
     * @version 创建时间：2015年6月13日 下午12:41:02
     */
    public static int dateWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int DAYOFWEEK = 0;
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
            DAYOFWEEK = 7;
        } else {
            DAYOFWEEK = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return DAYOFWEEK;
    }

    /**
     * 计算相差天、小时、分钟
     *
     * @param dateDiffSec
     * @param type
     * @return
     * @author 王敏
     * @version 创建时间：2015年6月13日 下午4:13:12
     */
    public static int dateDiffType(long dateDiffSec, String type) {
        if ("MINMORE".equals(type)) {
            return (int) (dateDiffSec % (60) > 0 ? (dateDiffSec / (60)) + 1 : dateDiffSec / (60));/* 一分钟的秒数 */
        } else if ("MINLESS".equals(type)) {
            int minless = (int) (dateDiffSec / (60));
            if (dateDiffSec % (60) > 0) minless++;
            return minless;
        } else if ("HOUR".equals(type)) {
            int hour = (int) (dateDiffSec / (60 * 60));
            if (dateDiffSec % (60 * 60) > 0) hour++;
            return hour;
        } else if ("DAY".equals(type)) {
            int day = (int) (dateDiffSec / (24 * 60 * 60));
            if (dateDiffSec % (24 * 60 * 60) > 0) day++;
            return day;
        } else {
            return -1;
        }
    }

    /**
     * 计算相差天数
     *
     * @return
     * @date 2017年10月10日 下午4:24:54
     * @author wangm
     */
    public static int dayDiff(Date beginDate, Date endDate) {
        return dateDiffType((endDate.getTime() - beginDate.getTime()) / 1000, "DAY");
    }


    /**
     * 计算相差小时
     *
     * @return
     * @date 2017年10月10日 下午4:24:54
     * @author wangm
     */
    public static int hourDiff(Date beginDate, Date endDate) {
        return dateDiffType((endDate.getTime() - beginDate.getTime()) / 1000, "HOUR");
    }
    /**
     * 计算相差秒数
     *
     * @return
     * @date 2017年10月10日 下午4:24:54
     * @author wangm
     */
    public static int secondDiff(Date beginDate, Date endDate) {
        return (int) ((endDate.getTime() - beginDate.getTime()) / 1000);
    }

    /**
     * 获取当前小时
     */
    public static int calendarGetHour(Date date) {
        return calendarGet(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取时间其中某个值
     */
    public static int calendarGet(Date date, int type) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(type);
    }

    /**
     * 取某一段时间内的每一天的日期
     *
     * @param dBegin
     * @param dEnd
     * @return
     */
    public static List<Date> findDates(Date dBegin, Date dEnd) {
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    /**
     * 取某一段时间内的每一天的日期
     *
     * @param dBegin
     * @param dEnd
     * @return
     */
    public static Set<String> findDatesStr(Date dBegin, Date dEnd) {
        Set setDate = new HashSet();
        try {
            Calendar calBegin = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calBegin.setTime(dBegin);
            setDate.add(DateSPUtil.format(calBegin.getTime(), DateSPUtil.YMD));

            Calendar calEnd = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calEnd.setTime(dEnd);
            // 测试此日期是否在指定日期之后
            while (dEnd.after(calBegin.getTime())) {
                // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
                calBegin.add(Calendar.DAY_OF_MONTH, 1);
                setDate.add(DateSPUtil.format(calBegin.getTime(), DateSPUtil.YMD));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return setDate;
    }



}
