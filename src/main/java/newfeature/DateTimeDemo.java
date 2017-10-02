package newfeature;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by robertpicyu on 2017/9/27.
 */
public class DateTimeDemo {
    public static void main(String[] args) {
        /**
         * Java8 中新的时间将时间（时分秒）、日期、时区分开
         * 相关类如下：
         *      Date(日期)   Time（时间）  Zoom（时区）（给人）
         *     ---------------------------------
         *         instant/clock(unix时间戳)(给机器)
         */


         /**
          * 原先的Date、以及新的Instant 、Clock是 给机器的时间，转化关系如下
         */
        Date dat = new Date();
        Instant instant = dat.toInstant();
        Clock clock = Clock.systemUTC();
        instant = clock.instant();
        instant.toEpochMilli();
        System.out.printf("t1: %s\nt2: %s\n%s",instant,System.currentTimeMillis(),clock.instant());

        /**
         *  原先SimpleDateFormat 非线程安全，而新API线程安全，可以复用！！
         *  Date\DateTime等都支持
         *      parse("",DateTimeFormatter.ofPattern(""))、
         *      format(DateTimeFormatter.ofPattern(""));
         *******************************************************************
         * 时间格式：
         *      Date                    '2011-12-03'
         *      Date+Timezoon           '2011-12-03+01:00'
         *      Time                    '10:15:30'
         *      Time+Timezoon           '10:15:30+01:00'
         *      DateTime                '2011-12-03T10:15:30'
         *      DateTime+Timezoon       '2011-12-03T10:15:30+01:00'
         *      时间戳默认格式           2011-12-03T10:15:30Z'         （GMT时间）
         *
         * 时区处理麻烦些，formatPattern中时区的对应关系如下
         * 常用的格式：字母\数值\字母数值组合, 对应z\Z\O ：
             V       time-zone ID                zone-id           America/Los_Angeles; Z; -08:30
             z       time-zone name              zone-name         Pacific Standard Time; PST
             O       localized zone-offset       offset-O          GMT+8; GMT+08:00; UTC-08:00;
             X       zone-offset 'Z' for zero    offset-X          Z; -08; -0830; -08:30; -083015; -08:30:15;
             x       zone-offset                 offset-x          +0000; -08; -0830; -08:30; -083015; -08:30:15;
             Z       zone-offset                 offset-Z          +0000; -0800; -08:00;
            （z\Z\O ）
         */
        DateTimeFormatter fomat = DateTimeFormatter.ofPattern("mm");
        LocalDate.parse("",fomat);

        /**
         * Date\DateTime等都支持
         *      parse("",DateTimeFormatter.ofPattern(""))、
         *      format(DateTimeFormatter.ofPattern(""));
         * Date 操作：
         *      1、构建：now()  、  of()、
         *      2、属性获取：
         *      3、移动：
         *      4、两Date操作：前后判断、时间差
         *  Date还延伸出出一些辅助类： YearMonth、MonthDay
         */
        LocalDate now = LocalDate.of(2014, 1, 15);
        LocalDate date = LocalDate.now();
        date.getYear();
        date.withDayOfMonth(1);
        date.plusDays(5);
        date.minusDays(5);
        date.isAfter(now);

        Period period = Period.between(now,date);
        period.getDays();

        YearMonth yearMonth = YearMonth.of(2018, Month.FEBRUARY);
        MonthDay monthDay = MonthDay.of(5,10);


        /**
         * Time 操作：
         *      1、构建：now()  、  of()
         *      2、属性获取：
         *      3、移动：
         *      4、两Date操作：前后判断
         */
        LocalTime time = LocalTime.of(14, 1, 15);
        LocalTime timeNow = LocalTime.now();
        time.getHour();
        time.minusHours(1);
        time.plusHours(1);
        time.isAfter(timeNow);

        /**
         * Data + Time 操作：
         *      1、构建：
         *      2、属性获取：
         *      3、移动：
         *      4、两Date操作：前后判断
         */
        LocalDateTime dateTime = now.atTime(time);      // atXXX（） 是 转化各种时间类的方法
        LocalDateTime dateTimeNow = LocalDateTime.now();
        dateTime.getHour();
        dateTime.minusHours(1);
        dateTime.plusHours(1);
        dateTime.isAfter(dateTimeNow);

        /**
         * Data + Time + Zoom 操作：
         *      1、构建：
         *      2、属性获取：
         *      3、移动：
         *      4、两Date操作：前后判断
         */
        ZonedDateTime zonedTime = dateTime.atZone(ZoneId.of("GMT"));      // atXXX（） 是 转化各种时间类的方法
        ZonedDateTime zonedTimeNow = ZonedDateTime.now();
        dateTime.getHour();
        dateTime.minusHours(1);
        dateTime.plusHours(1);
        dateTime.isAfter(dateTimeNow);

    }
}
