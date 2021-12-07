package org.zzj.common.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期工具类
 *
 * @version 1.0
 */
public class DateUtil extends DateUtils {
    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        System.out.println(DateUtil.dateFormatYYMMDD(DateUtil.getMonthIntegral(new Date(),1)));
        System.out.println(DateUtil.addMonths(new Date(),1));

    }

    public static final String DF_MM_DD = "MM月dd日";
    public static final String DF_YYYY_MM = "yyyy-MM";
    public static final String DF_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DF_YYYY_MM_DD2 = "yyyyMMdd";
    public static final String DF_HH_MM = "HH:mm";
    public static final String DF_HH = "HH";
    public static final String DF_YYYYMM = "yyyyMM";
    public static final String DF_YYYYMMDD = "yyyyMMdd";
    public static final String DF_YYYY = "yyyy";
    public static final String DF_MM = "MM";
    public static final String DF_DD = "dd";
    public static final String DF_YY_MM_DD_HH_MI = "yyyy-MM-dd HH:mm";
    public static final String DF_YY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DF_YYMMDDHHMISS = "yyyyMMddHHmmss";
    public static final String DF_YYMMDDHH = "yyyyMMddHH";
    public static final String DF_YY_MM_DD_HH_MI_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static SimpleDateFormat DATE_FORMAT_MM_DD = new SimpleDateFormat("MM月dd日");
    public static SimpleDateFormat DATE_FORMAT_YYYY_MM = new SimpleDateFormat("yyyy-MM");
    public static SimpleDateFormat DATE_FORMAT_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat DATE_FORMAT_YYYY_MM_DD_CN = new SimpleDateFormat("yyyy年MM月dd日");
    public static SimpleDateFormat DATE_FORMAT_YYYY_MM_DD2 = new SimpleDateFormat("yyyyMMdd");
    public static SimpleDateFormat DATE_FORMAT_HH_MM = new SimpleDateFormat("HH:mm");
    public static SimpleDateFormat DATE_FORMAT_HH_MM2 = new SimpleDateFormat("HHmm");
    public static SimpleDateFormat DATE_FORMAT_HH = new SimpleDateFormat("HH");
    public static SimpleDateFormat DATE_FORMAT_YYYYMM = new SimpleDateFormat("yyyyMM");
    public static SimpleDateFormat DATE_FORMAT_YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
    public static SimpleDateFormat DATE_FORMAT_YYYY = new SimpleDateFormat("yyyy");
    public static SimpleDateFormat DATE_FORMAT_MM = new SimpleDateFormat("MM");
    public static SimpleDateFormat DATE_FORMAT_DD = new SimpleDateFormat("dd");
    public static SimpleDateFormat DATE_FORMAT_YY_MM_DD_HH_MI = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static SimpleDateFormat DATE_FORMAT_YY_MM_DD_HH_MI_CN = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
    public static SimpleDateFormat DATE_FORMAT_YY_MM_DD_HH_MI_SS_CN = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    public static SimpleDateFormat DATE_FORMAT_YY_MM_DD_HH_MI_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat DATE_FORMAT_YYMMDDHHMISS = new SimpleDateFormat(DF_YYMMDDHHMISS);

    public static java.sql.Date parseDate(String dateStr, String format) {
        if (dateStr == null)
            return null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return new java.sql.Date(dateFormat.parse(dateStr).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static java.sql.Date parseDate(String dateStr) {
        if (dateStr == null)
            return null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return new java.sql.Date(dateFormat.parse(dateStr).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static java.sql.Date parseDate(Date date) {
        try {
            return new java.sql.Date(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseUtilDate(String dateStr, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return new Date(dateFormat.parse(dateStr).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseUtilDate(Integer dateId) {
        try {
            String dateStr = String.valueOf(dateId);
            String format = "yyyyMMdd".substring(0, String.valueOf(dateId).length());
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return new Date(dateFormat.parse(dateStr).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static java.sql.Date parseDate(Integer dateId) {
        try {
            String dateStr = String.valueOf(dateId);
            String format = "yyyyMMdd".substring(0, String.valueOf(dateId).length());
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return new java.sql.Date(dateFormat.parse(dateStr).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date sqlDate2UtilDate(java.sql.Date sqlDate) {
        try {
            return new Date(sqlDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String utilDate2Str(Date date) {
        return dateFormatYYMMDD(date);
    }

    public static Date getDateYYMMDD(Date date) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
    public static String sqlDate2str(java.sql.Date sqlDate) {
        return dateFormatYYMMDD(sqlDate2UtilDate(sqlDate));
    }

    public static java.sql.Date trunc(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return parseDate(dateFormat.format(date), format);
    }

    public static Date getCurrentTime() {
        return parseDate(DATE_FORMAT_YY_MM_DD_HH_MI_SS.format(new Date()), "yyyy-MM-dd HH:mm:ss");
    }

    public static Date getCurrentDay() {
        return parseDate(DATE_FORMAT_YYYY_MM_DD.format(new Date()), "yyyy-MM-dd");
    }

    public static Date getCurrentMonth() {
        return parseDate(DATE_FORMAT_YYYY_MM.format(new Date()), "yyyy-MM");
    }

    public static java.sql.Date getLastYear(Integer lastN) {
        int year = Calendar.getInstance().get(1);
        year -= lastN.intValue();
        return parseDate(String.valueOf(year), "yyyy");
    }

    public static java.sql.Date getLastYear(Date date, Integer lastN) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(1, -lastN.intValue());
        return parseDate(c.getTime());
    }

    public static java.sql.Date getLastMonth(Integer lastN) {
        Date now = new Date();
        Date lastNmonth = DateUtils.addMonths(now, -lastN.intValue());
        return parseDate(DATE_FORMAT_YYYY_MM.format(lastNmonth), "yyyy-MM");
    }

    public static Date getLastMonth(Date date, Integer lastN) {
        Date lastNmonth = DateUtils.addMonths(date, -lastN.intValue());
        return parseDate(DATE_FORMAT_YYYY_MM.format(lastNmonth), "yyyy-MM");
    }

    public static Date getLassWeek(Date date, Integer lastN) {
        Date mondayDate = DateUtils.addWeeks(date, -lastN.intValue());
        return parseDate(DATE_FORMAT_YYYY_MM_DD.format(mondayDate), "yyyy-MM-dd");
    }

    public static Date getLastWeek(Integer lastN) {
        Date now = new Date();
        Date mondayDate = DateUtils.addWeeks(now, -lastN.intValue());
        return parseDate(DATE_FORMAT_YYYY_MM_DD.format(mondayDate), "yyyy-MM-dd");
    }

    public static Date getLastWeekMondayDate() {
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);

        int iDayOfWeek = c.get(7);

        int lastN = 2 - iDayOfWeek - 7;
        Date lastWeekMondayDate = getLastDay(now, Integer.valueOf(Math.abs(lastN)));
        return lastWeekMondayDate;
    }

    public static String getWeekByDate(Date date) {
        String week = "";

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(7);

        switch (dayOfWeek) {
            case 1:
                week = "星期日";
                break;
            case 2:
                week = "星期一";
                break;
            case 3:
                week = "星期二";
                break;
            case 4:
                week = "星期三";
                break;
            case 5:
                week = "星期四";
                break;
            case 6:
                week = "星期五";
                break;
            case 7:
                week = "星期六";
        }

        return week;
    }

    public static java.sql.Date getLastDay(Integer lastN) {
        Date now = new Date();
        Date lastNmonth = DateUtils.addDays(now, -lastN.intValue());
        return parseDate(DATE_FORMAT_YYYY_MM_DD.format(lastNmonth), "yyyy-MM-dd");
    }

    public static String getLastDayPart(Integer lastN) {
        Date now = new Date();
        Date lastNmonth = DateUtils.addDays(now, -lastN.intValue());
        return DATE_FORMAT_DD.format(lastNmonth);
    }

    public static Date getLastDay(Date date, Integer lastN) {
        Date lastNmonth = DateUtils.addDays(date, -lastN.intValue());
        return parseDate(DATE_FORMAT_YYYY_MM_DD.format(lastNmonth), "yyyy-MM-dd");
    }

    public static Date getNextDay(Integer nextN) {
        Date now = new Date();
        Date nextNDay = DateUtils.addDays(now, nextN.intValue());
        return parseDate(DATE_FORMAT_YYYY_MM_DD.format(nextNDay), "yyyy-MM-dd");
    }

    public static Date getNow() {
        Date now = new Date();
        return new Date(now.getTime());
    }

    public static String toString(Date d, String format) {
        return dateFormat(d, format);
    }

    public static String toString(Date d) {
        return dateFormat(d, "yyyy-MM-dd");
    }

    public static String dateFormat(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static String dateFormatMMDD(Date date) {
        return DATE_FORMAT_MM_DD.format(date);
    }

    public static String dateFormatMMDDcn(Date date) {
        long time = trunc(date, "yyyy-MM-dd").getTime();
        if (time == getLastDay(Integer.valueOf(0)).getTime()) {
            return "今天";
        }
        if (time == getLastDay(Integer.valueOf(1)).getTime()) {
            return "昨天";
        }
        if (time == getLastDay(Integer.valueOf(2)).getTime()) {
            return "前天";
        }
        return DATE_FORMAT_MM_DD.format(date);
    }

    public static String dateFormatYYMM(Date date) {
        if (date == null)
            return "";
        return DATE_FORMAT_YYYY_MM.format(date);
    }

    public static String dateFormatYYMMDD(Date date) {
        if (date == null)
            return "";
        return DATE_FORMAT_YYYY_MM_DD.format(date);
    }

    public static String dateFormatYYMMDDHHMICN(Date date) {
        if (date == null)
            return "";
        return DATE_FORMAT_YY_MM_DD_HH_MI_CN.format(date);
    }

    public static String dateFormatYYMMDDHHMISSCN(Date date) {
        if (date == null)
            return "";
        return DATE_FORMAT_YY_MM_DD_HH_MI_SS_CN.format(date);
    }

    public static String dateFormatYYMMDDCN(Date date) {
        if (date == null)
            return "";
        return DATE_FORMAT_YYYY_MM_DD_CN.format(date);
    }

    public static String dateFormatYYYYMMDDHHMMSS(Date date) {
        if (date == null)
            return "";
        return DATE_FORMAT_YY_MM_DD_HH_MI_SS.format(date);
    }

    public static String dateFormatYYYYMMDDHHMMSS2(Date date) {
        if (date == null)
            return "";
        return DATE_FORMAT_YYMMDDHHMISS.format(date);
    }

    public static String dateFormatHHMM(Date date) {
        return DATE_FORMAT_HH_MM.format(date);
    }

    public static String dateFormatHHMM2(Date date) {
        return DATE_FORMAT_HH_MM2.format(date);
    }

    public static String dateFormatHH(Date date) {
        return DATE_FORMAT_HH.format(date);
    }

    public static String dateFormatDD(Date date) {
        return Integer.valueOf(DATE_FORMAT_DD.format(date)).toString();
    }

    public static String dateFormatQua(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(2) + 1;
        String reString = "";
        if (month <= 3)
            reString = "第一季度";
        else if (month <= 6)
            reString = "第二季度";
        else if (month <= 9)
            reString = "第三季度";
        else if (month <= 12) {
            reString = calendar.get(0) + "年第四季度";
        }
        return reString;
    }

    public static String translateFormat(String s) {
        return translateFormat(s, "yyyy-MM-dd");
    }

    public static String translateFormat(String s, String format) {
        return DATE_FORMAT_YYYY_MM_DD2.format(parseDate(s, format));
    }

    public static String translateFormatYYYYMMDD(String s) {
        return DATE_FORMAT_YYYY_MM_DD.format(parseDate(s));
    }

    public static String getYear(Date date) {
        return DATE_FORMAT_YYYY.format(date);
    }

    public static String getYearMonth(Date date) {
        return DATE_FORMAT_YYYYMM.format(date);
    }

    public static String getYearMonthDay(Date date) {
        return DATE_FORMAT_YYYYMMDD.format(date);
    }

    public static String getMonth(Date date) {
        return Integer.valueOf(DATE_FORMAT_MM.format(date)).toString();
    }

    public static Integer getMonth2(Date date) {
        return Integer.valueOf(DATE_FORMAT_MM.format(date));
    }

    public static String getDay(Date date) {
        return Integer.valueOf(DATE_FORMAT_DD.format(date)).toString();
    }
    public static Integer getDay2(Date date) {
        return Integer.valueOf(DATE_FORMAT_DD.format(date));
    }


    public static String getCurrentYear() {
        return DATE_FORMAT_YYYY.format(new Date());
    }

    public static Integer getCurrentYearId() {
        Calendar calendar = Calendar.getInstance();
        return Integer.valueOf(calendar.get(1));
    }

    public static Integer getCurrentMonthId() {
        Calendar calendar = Calendar.getInstance();
        return Integer.valueOf(calendar.get(2) + 1);
    }

    public static Integer getCurrentDateId() {
        Calendar calendar = Calendar.getInstance();
        return Integer.valueOf(calendar.get(5));
    }

    /**
     * 计算 second 秒后的时间
     *
     * @param date
     * @param second
     * @return
     */
    public static Date addSecond(Date date, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        ;
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 计算 minute 分钟后的时间
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 计算 hour 小时后的时间
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    /**
     * 判断日期格式，并转化为时间
     *
     * @param dateStr 带时分秒 不带时分秒
     * @return
     */
    public static Date parseUtilDateByString(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DF_YY_MM_DD_HH_MI_SS);
            return new Date(dateFormat.parse(dateStr).getTime());
        } catch (Exception e) {
            try {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat(DF_YYYY_MM_DD);
                return new Date(dateFormat2.parse(dateStr).getTime());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 取多少周,返回YYYY-uu形式
     *
     * @return
     */
    public static String dateFormatYYUU(Date date) {
        if (date == null)
            return "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return DATE_FORMAT_YYYY.format(date) + "-" + cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 判断是当前周的第几天
     *
     * @return
     */
    public static int getWeekNumByDate(Date date) {
        if (date == null)
            return 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(7);
    }

    /**
     * 判断两天相差多少天
     *
     * @return
     */
    public static int daysOfTwo(Date sdate, Date edate) {
        if (sdate == null || edate == null)
            return 0;

        Date sdateByday = DateUtils.truncate(sdate, Calendar.DATE);
        Date edateByday = DateUtils.truncate(edate, Calendar.DATE);
        long diff = Math.abs(sdateByday.getTime() - edateByday.getTime());

//		Calendar aCalendar = Calendar.getInstance();
//		aCalendar.setTime(sdate);
//		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
//
//		aCalendar.setTime(edate);
//		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
//		if (day2 > day1) {
//			return day2 - day1;
//		}
//		return day1 - day2;		
        long diffDay = diff / (1000 * 3600 * 24);
        return (int) diffDay;

    }

    /**
     * 时间相加减
     *
     * @param startDate
     * @param unitType  10-- HOUR   11--DAY_OF_MONTH  more Calendar
     *                  Calendar.YEAR	Calendar.MONTH Calendar.DAY_OF_YEAR
     * @param range
     * @return
     */
    public static Date dateAdd(Date startDate, int unitType, int range) {
        Calendar cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        cal.setTime(startDate);
        cal.add(unitType, range);//  10-- HOUR   11--DAY_OF_MONTH
//	    	return DateUtil.dateFormat(cal.getTime(),DateUtil.DF_YY_MM_DD_HH_MI_SS);
        return cal.getTime();
    }


    //假设传入的日期格式是yyyy-MM-dd HH:mm:ss, 也可以传入yyyy-MM-dd，如2018-1-1或者2018-01-01格式

    public static boolean isValidDate(String strDate) {
        if (StringUtil.isBlank(strDate)) {
            return false;
        }

        String pattern = DateUtil.DF_YYYY_MM_DD;
        if (strDate.length() > 10) {
            pattern = DateUtil.DF_YY_MM_DD_HH_MI_SS;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2018-02-29会被接受，并转换成2018-03-01

            format.setLenient(false);
            Date date = format.parse(strDate);

            //判断传入的yyyy年-MM月-dd日 字符串是否为数字
            String[] sArray = strDate.split("-");
            for (String s : sArray) {
                boolean isNum = s.matches("[0-9]+");
                //+表示1个或多个（如"3"或"225"），*表示0个或多个（[0-9]*）（如""或"1"或"22"），?表示0个或1个([0-9]?)(如""或"7")
                if (!isNum) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }

        return true;
    }


    /**
     * 将秒数转换为日时分秒，
     *
     * @param second
     * @return
     */
    public static String secondToTime(long second) {
        long days = second / 86400;            //转换天数
        second = second % 86400;            //剩余秒数
        long hours = second / 3600;            //转换小时
        second = second % 3600;                //剩余秒数
        long minutes = second / 60;            //转换分钟
        second = second % 60;                //剩余秒数
        if (days > 0) {
            return days + "天" + hours + "小时" + minutes + "分" + second + "秒";
        } else {
            return hours + "小时" + minutes + "分" + second + "秒";
        }
    }

    /**
     * 将日期转换为日时分秒
     *
     * @param date
     * @return
     */
    public static String dateToTime(String date, String dateStyle) {
        SimpleDateFormat format = new SimpleDateFormat(dateStyle);
        try {
            Date oldDate = format.parse(date);
            long time = oldDate.getTime();                    //输入日期转换为毫秒数
            long nowTime = System.currentTimeMillis();        //当前时间毫秒数
            long second = nowTime - time;                    //二者相差多少毫秒
            second = second / 1000;                            //毫秒转换为妙
            long days = second / 86400;
            second = second % 86400;
            long hours = second / 3600;
            second = second % 3600;
            long minutes = second / 60;
            second = second % 60;
            if (days > 0) {
                return days + "天" + hours + "小时" + minutes + "分" + second + "秒";
            } else {
                return hours + "小时" + minutes + "分" + second + "秒";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取整点
     *
     * @param amount 传递的小时
     * @return
     */
    public static Date getIntegral(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), amount, 0, 0);
        return calendar.getTime();
    }

    /**
     * 获取整点
     *
     * @param amount 传递的小时
     * @return
     */
    public static Date getMonthIntegral(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),amount, 0, 0, 0);
        return calendar.getTime();
    }
    /**
     * 获取整点
     *
     * @param amount 传递的小时
     * @return
     */
    public static Date getMonth(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), amount, 0, 0);
        return calendar.getTime();
    }
    /**
     * 判断当前日期是星期几<br>
     * <br>
     *
     * @param pTime 修要判断的时间<br>
     * @return dayForWeek 判断结果<br>
     * @Exception 发生异常<br>
     */
    public static int dayForWeek(Date date) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }
}
