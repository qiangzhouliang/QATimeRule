package com.qzl.timerule.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>项目名称 : bask</p>
 * <p>创建时间 : 2018-03-27 下午04:14:17</p>
 * <p>类描述 :         </p>
 *
 * @author <a href=" ">hanjp</a>
 * 日期帮助类
 * @version 1.0.0
 */
public class TimeHelper {
    private static String CurrentTime;

    private static String CurrentDate;

    /**
     * 得到当前的年份 返回格式:yyyy
     *
     * @return String
     */
    public static String getCurrentYear() {
        Date NowDate = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        return formatter.format(NowDate);
    }

    /**
     * 得到当前的月份 返回格式:yyyy-MM
     *
     * @return String
     */
    public static String getCurrentYearAndMonth() {
        Date NowDate = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        return formatter.format(NowDate);
    }

    /**
     * 得到当前的月份 返回格式:MM
     *
     * @return String
     */
    public static String getCurrentMonth() {
        Date NowDate = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        return formatter.format(NowDate);
    }

    /**
     * 得到当前的日期 返回格式:dd
     *
     * @return String
     */
    public static String getCurrentDay() {
        Date NowDate = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        return formatter.format(NowDate);
    }

    public static String getYesterdayDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        CurrentTime = formatter.format(cal.getTime());
        return CurrentTime;
    }

    /**
     * 得到当前的时间，精确到毫秒,共19位 返回格式:yyyy-MM-dd HH:mm:ss
     *
     * @return String
     */
    public static String getCurrentTime() {
        Date NowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CurrentTime = formatter.format(NowDate);
        return CurrentTime;
    }

    /**
     * yyyy-MM-dd HH
     *
     * @return
     */
    public static String getCurrentTimeHour() {
        Date NowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");
        CurrentTime = formatter.format(NowDate);
        return CurrentTime;
    }

    /**
     * 获取当前系统时间精确到分钟
     *
     * @return
     */
    public static String getCurrentTimeDd() {
        Date NowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        CurrentTime = formatter.format(NowDate);
        return CurrentTime;
    }

    public static String getCurrentCompactTime() {
        Date NowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        CurrentTime = formatter.format(NowDate);
        return CurrentTime;
    }

    public static String getYesterdayCompactTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.DATE, -1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        CurrentTime = formatter.format(cal.getTime());
        return CurrentTime;
    }

    public static String getYesterdayCompactTimeForFileName() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.DATE, -1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CurrentTime = formatter.format(cal.getTime());
        return CurrentTime;
    }

    /**
     * 得到当前的日期,共10位 返回格式：yyyy-MM-dd
     *
     * @return String
     */
    public static String getCurrentDate() {
        Date NowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        CurrentDate = formatter.format(NowDate);
        return CurrentDate;
    }

    /**
     * 得到当前的日期,共10位 返回格式：yyyy-MM-dd
     *
     * @return String
     */
    public static String getCurrentDate1() {
        Date NowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        CurrentDate = formatter.format(NowDate);
        return CurrentDate;
    }

    /**
     * 得到当月的第一天,共10位 返回格式：yyyy-MM-dd
     *
     * @return String
     */
    public static String getCurrentFirstDate() {
        Date NowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-01");
        CurrentDate = formatter.format(NowDate);
        return CurrentDate;
    }

    /**
     * 得到当天,共8位 返回格式：yyyyMMdd
     *
     * @return String
     */
    public static String getCurrentDate2() {
        Date NowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        CurrentDate = formatter.format(NowDate);
        return CurrentDate;
    }

    /*
     * 获取时间戳
     * */
    public static long getTimeStamp(String time) {
        long timeStamp = 0;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = formatter.parse(time);
            timeStamp = date.getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeStamp;
    }

    /**
     * 得到当月的最后一天,共10位 返回格式：yyyy-MM-dd
     *
     * @return String
     * @throws ParseException
     */
    public static String getCurrentLastDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = null;
        try {
            Date date = formatter.parse(getCurrentFirstDate());
            calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            return formatDate(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 常用的格式化日期
     *
     * @param date Date
     * @return String
     */
    public static String formatDate(Date date) {
        return formatDateByFormat(date, "yyyy-MM-dd");
    }

    /**
     * @Author qzl
     * @Description 格式化时间 yyyy-MM-dd HH:mm:ss
     * @Date 6:09 下午 2021/10/28
     **/

    public static String formatDateAll(Date date) {
        return formatDateByFormat(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 以指定的格式来格式化日期
     *
     * @param date   Date
     * @param format String
     * @return String
     */
    public static String formatDateByFormat(Date date, String format) {
        String result = "";
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 得到当前日期加上某一个整数的日期，整数代表天数 输入参数：currentdate : String 格式 yyyy-MM-dd HH:mm:ss add_day :
     * int 返回格式：yyyy-MM-dd HH:mm:ss
     */
    public static String addDayAll(String currentdate, int add_day) {
        GregorianCalendar gc = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int year, month, day, hour, minute, second;

        try {
            year = Integer.parseInt(currentdate.substring(0, 4));
            month = Integer.parseInt(currentdate.substring(5, 7)) - 1;
            day = Integer.parseInt(currentdate.substring(8, 10));

            hour = Integer.parseInt(currentdate.substring(11, 13));
            minute = Integer.parseInt(currentdate.substring(14, 16));
            second = Integer.parseInt(currentdate.substring(17, 19));

            gc = new GregorianCalendar(year, month, day, hour, minute, second);
            gc.add(GregorianCalendar.DATE, add_day);

            return formatter.format(gc.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到当前日期加上某一个整数的日期，整数代表天数 输入参数：currentdate : String 格式 yyyy-MM-dd add_day :
     * int 返回格式：yyyy-MM-dd
     */
    public static String addDay(String currentdate, int add_day) {
        GregorianCalendar gc = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        int year, month, day;

        try {
            year = Integer.parseInt(currentdate.substring(0, 4));
            month = Integer.parseInt(currentdate.substring(5, 7)) - 1;
            day = Integer.parseInt(currentdate.substring(8, 10));

            gc = new GregorianCalendar(year, month, day);
            gc.add(GregorianCalendar.DATE, add_day);

            return formatter.format(gc.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到当前日期加上某一个整数的日期，整数代表月数 输入参数：currentdate : String 格式 yyyy-MM-dd add_month :
     * int 返回格式：yyyy-MM-dd
     */
    public static String addMonth(String currentdate, int add_month) {
        GregorianCalendar gc = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        int year, month, day;

        try {
            year = Integer.parseInt(currentdate.substring(0, 4));
            month = Integer.parseInt(currentdate.substring(5, 7)) - 1;
            day = Integer.parseInt(currentdate.substring(8, 10));

            gc = new GregorianCalendar(year, month, day);
            gc.add(GregorianCalendar.MONTH, add_month);

            return formatter.format(gc.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到endTime比beforeTime晚几个月，整数代表月数 输入参数：endTime、beforeTime : String 格式前7位的格式为 yyyy-MM
     */
    public static int monthDiff(String beforeTime, String endTime) {
        if (beforeTime == null || endTime == null) {
            return 0;
        }
        int beforeYear, endYear, beforeMonth, endMonth;
        try {
            beforeYear = Integer.parseInt(beforeTime.substring(0, 4));
            endYear = Integer.parseInt(endTime.substring(0, 4));
            beforeMonth = Integer.parseInt(beforeTime.substring(5, 7)) - 1;
            endMonth = Integer.parseInt(endTime.substring(5, 7)) - 1;
            return (endYear - beforeYear) * 12 + (endMonth - beforeMonth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 得到当前日期加上某一个整数的分钟 输入参数：currentdatetime : String 格式 yyyy-MM-dd HH:mm:ss
     * add_minute : int 返回格式：yyyy-MM-dd HH:mm:ss
     */
    public static String addMinute(String currentdatetime, int add_minute) {
        GregorianCalendar gc = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int year, month, day, hour, minute, second;

        try {
            year = Integer.parseInt(currentdatetime.substring(0, 4));
            month = Integer.parseInt(currentdatetime.substring(5, 7)) - 1;
            day = Integer.parseInt(currentdatetime.substring(8, 10));

            hour = Integer.parseInt(currentdatetime.substring(11, 13));
            minute = Integer.parseInt(currentdatetime.substring(14, 16));
            second = Integer.parseInt(currentdatetime.substring(17, 19));

            gc = new GregorianCalendar(year, month, day, hour, minute, second);
            gc.add(GregorianCalendar.MINUTE, add_minute);
            return formatter.format(gc.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到当前日期减去某一个整数的分钟 输入参数：currentdatetime : String 格式 yyyy-MM-dd HH:mm:ss
     * minute : int 返回格式：yyyy-MM-dd HH:mm:ss
     */
    public static String reductionMinute(String currentdatetime, int minute) {
        GregorianCalendar gc = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(currentdatetime);
            long Time = (date.getTime() / 1000) - 60 * minute;
            date.setTime(Time * 1000);
            return format.format(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到当前日期加上某一个整数的秒 输入参数：currentdatetime : String 格式 yyyy-MM-dd HH:mm:ss
     * add_second : int 返回格式：yyyy-MM-dd HH:mm:ss
     */
    public static String addSecond(String currentdatetime, int add_second) {
        GregorianCalendar gc = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int year, month, day, hour, minute, second;

        try {
            year = Integer.parseInt(currentdatetime.substring(0, 4));
            month = Integer.parseInt(currentdatetime.substring(5, 7)) - 1;
            day = Integer.parseInt(currentdatetime.substring(8, 10));

            hour = Integer.parseInt(currentdatetime.substring(11, 13));
            minute = Integer.parseInt(currentdatetime.substring(14, 16));
            second = Integer.parseInt(currentdatetime.substring(17, 19));

            gc = new GregorianCalendar(year, month, day, hour, minute, second);
            gc.add(GregorianCalendar.SECOND, add_second);

            return formatter.format(gc.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String addMinute1(String currentdatetime, int add_minute) {
        GregorianCalendar gc = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        int year, month, day, hour, minute, second;

        try {
            year = Integer.parseInt(currentdatetime.substring(0, 4));
            month = Integer.parseInt(currentdatetime.substring(5, 7)) - 1;
            day = Integer.parseInt(currentdatetime.substring(8, 10));

            hour = Integer.parseInt(currentdatetime.substring(8, 10));
            minute = Integer.parseInt(currentdatetime.substring(8, 10));
            second = Integer.parseInt(currentdatetime.substring(8, 10));

            gc = new GregorianCalendar(year, month, day, hour, minute, second);
            gc.add(GregorianCalendar.MINUTE, add_minute);

            return formatter.format(gc.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date parseDate(String sDate) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = bartDateFormat.parse(sDate);
            return date;
        } catch (Exception ex) {
        }
        return null;
    }

    public static Date parseDate2(String sDate) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy/MM/dd");

        try {
            Date date = bartDateFormat.parse(sDate);
            return date;
        } catch (Exception ex) {
        }
        return null;
    }

    /**
     * 解析日期及时间
     *
     * @param sDateTime 日期及时间字符串
     * @return 日期
     */
    public static Date parseDateTime(String sDateTime) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        try {
            Date date = bartDateFormat.parse(sDateTime);
            return date;
        } catch (Exception ex) {
        }
        return null;
    }

    /**
     * 取得一个月的天数 date:yyyy-MM-dd
     *
     * @throws ParseException
     */
    public static int getTotalDaysOfMonth(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();

        Date date = null;
        try {
            date = sdf.parse(strDate);
            calendar.setTime(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 天数
        return day;
    }


    public static long getDateSubDay(String startDate, String endDate) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long theday = 0;
        try {
            calendar.setTime(sdf.parse(startDate));
            long timethis = calendar.getTimeInMillis();
            calendar.setTime(sdf.parse(endDate));
            long timeend = calendar.getTimeInMillis();
            theday = (timeend - timethis) / (1000 * 60 * 60 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return theday;
    }

    /**
     * 方法描述 :获取两个日期时间差
     *
     * @param sDateTime
     * @param eDateTime
     * @return
     * @throws ParseException
     */
    public static String getPlusTime(String sDateTime, String eDateTime) throws ParseException {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date ssDateTime = myFormatter.parse(sDateTime);
        Date eeDateTime = myFormatter.parse(eDateTime);
        long l = (eeDateTime.getTime() - ssDateTime.getTime());
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        return "" + day + "天" + hour + "小时" + min + "分" + s + "秒";
    }

    /**
     * 方法描述 :获取两个日期时间差
     *
     * @param sDateTime
     * @param eDateTime
     * @return
     * @throws ParseException
     */
    public static int getTime(String sDateTime, String eDateTime) throws ParseException {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date ssDateTime = myFormatter.parse(sDateTime);
        Date eeDateTime = myFormatter.parse(eDateTime);
        long l = (eeDateTime.getTime() - ssDateTime.getTime());
        long day = l / (24 * 60 * 60 * 1000);
        return (int) day;
    }

    /**
     * 方法描述 : 获取时间差
     *
     * @param sDateTime
     * @param eDateTime
     * @return
     * @throws ParseException
     */
    public static long getPlusTotalTime(String sDateTime, String eDateTime) throws ParseException {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date ssDateTime = myFormatter.parse(sDateTime);
        Date eeDateTime = myFormatter.parse(eDateTime);
        return (eeDateTime.getTime() - ssDateTime.getTime());
    }

    /**
     * 根据一个日期，返回是星期几的字符串
     *
     * @param sdate
     * @return
     */
    public static String getWeek(String sdate) {
        // 再转换为时间
        Date date = TimeHelper.strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    public static String convertToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.format(date);
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到二个日期间的间隔天数
     */
    public static String getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }

    /**
     * 方法描述 : 将毫秒装换成日期
     *
     * @return
     * @throws ParseException
     */
    public static String getTimeByMillis(long now) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(now);
            return formatter.format(calendar.getTimeInMillis());
        } catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 格式为2018-05-14T16:00:00.000+0000
     *
     * @return
     * @throws ParseException
     */
    public static String getTimes() {
        try {
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS+0000");
            Date date = new Date();
            return simpleDateFormat2.format(date);
        } catch (Exception e) {
            return "2018-05-14T16:00:00.000+0000";
        }

    }


    /**
     * 获取月旬 三旬: 上旬1-10日 中旬11-20日 下旬21-31日
     *
     * @return
     */
    public static int getTenDay(String sDateTime) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        Date date = new Date();
        try {
            date = bartDateFormat.parse(sDateTime);
        } catch (Exception ex) {
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_MONTH);
        if (i < 11)
            return 1;
        else if (i < 21)
            return 2;
        else
            return 3;
    }

    /**
     * @Author qzl
     * @Description 格式化中间带T的时间
     * @Date 3:54 下午 2021/10/21
     **/
    public static String formatTimeCenterT(String time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        //格式化，转当地时区时间
        Date after = null;
        try {
            after = df.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return time;
        }
        df.applyPattern("yyyy-MM-dd HH:mm:ss");
        //默认时区
        df.setTimeZone(TimeZone.getDefault());
        return df.format(after);
    }


    /**
     * HH:mm:ss
     *
     * @return
     */
    public static String getHourMinSecond(String sDateTime) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long time = simpleDateFormat.parse(sDateTime).getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            CurrentTime = formatter.format(new Date(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CurrentTime;
    }

    /**
     * yyyy-MM-dd
     *
     * @param sDateTime
     * @return
     */
    public static String getLastDayYearMonthDay(String sDateTime) {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long time = simpleDateFormat.parse(sDateTime).getTime();
            time -= 24 * 60 * 60 * 1000;
            CurrentDate = simpleDateFormat.format(new Date(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CurrentDate;
    }

    /**
     * yyyy-MM-dd
     *
     * @param sDateTime
     * @return
     */
    public static String getNextDayYearMonthDay(String sDateTime) {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long time = simpleDateFormat.parse(sDateTime).getTime();
            time += 24 * 60 * 60 * 1000;
            CurrentDate = simpleDateFormat.format(new Date(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CurrentDate;
    }

    /**
     * yyyy-MM-dd
     *
     * @param sDateTime
     * @return
     */
    public static long getCurrentDayStart(String sDateTime) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long time = simpleDateFormat.parse(sDateTime).getTime();
            return time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取毫秒时间
     */
    public static long getTimeMillis(String sTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormat.parse(sTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
