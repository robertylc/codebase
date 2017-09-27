package newfeature;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Created by robertpicyu on 2017/9/27.
 */
public class DateTimeDemo {
    public static void main(String[] args) {
        /**
         * Java8 中新的时间将时间（时分秒）、日期、时区分开
         * 相关类如下：
         *      Date(日期)   Time（时间）  Zoom（时区）
         *     ---------------------------------
         *         instant/clock(unix时间戳)
         */
        Clock clock = Clock.systemUTC();
        Long t1 = clock.millis();
        System.out.printf("t1: %s\nt2: %s\n%s",t1,System.currentTimeMillis(),clock.instant());

        /**
         * Date 操作：
         *      1、构建：now()  、  of()
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

        /* 格式化：原先SimpleDateFormat 非线程安全，而新API线程安全 */
        DateTimeFormatter fomat = DateTimeFormatter.ofPattern("mm ");
    }
}
