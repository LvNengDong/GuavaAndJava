package cn.lnd.date;

import com.google.common.base.Strings;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.util.Date;
import java.util.Locale;

/**
 * @Author lnd
 * @Description
 * @Date 2022/7/16 15:28
 */
public class DateHandlerTest {


    /**
     * Part1：构造对象
     */
    @Test
    public void create(){
        DateTime nowWithTimezone = new DateTime();
        LocalDateTime localDateTime = new LocalDateTime();
        LocalDate localDate = new LocalDate();
        LocalTime localTime = new LocalTime();
        /*
         * 在 joda-time 类库中，以 local 命名的，都指的是本地时间，没有时区信息。
         * */
        System.out.println(nowWithTimezone);
        System.out.println(localDateTime);
        System.out.println(localDate);
        System.out.println(localTime);
        /* 执行以上代码，输出结果为：
        --------------------------------------
        2022-07-16T15:34:41.079+08:00
        2022-07-16T15:34:41.128
        2022-07-16
        15:34:41.130
        ----------------------------------- */

        // 使用指定日期构造对象
        DateTime dt1 = new DateTime(1618119910486L);
        DateTime dt2 = new DateTime(new Date());
        DateTime dt3 = new DateTime("2021-07-16T15:34:41.079+08:00");
        DateTime dt4 = new DateTime(2008,8,8,7,34,28); // 年月日时分秒
        System.out.println(dt1);
        System.out.println(dt2);
        System.out.println(dt3);
        System.out.println(dt4);

        // 将 DateTime 对象转为 JDK 中的 Date 对象
        Date date = dt4.toDate();

        // 获取某天的 0点0分0秒0厘
        /*case1：*/
        DateTime dt5 = DateTime.now().withTime(0, 0, 0, 0);
        /*case2：*/
        DateTime dt6 = DateTime.now().withTimeAtStartOfDay();
    }

    /**
     * Part2：格式化
     */
    @Test
    public void format(){
        DateTime dateTime = new DateTime();
        /*
         *   DateTime --> Str
         *   joda-time 重写了 toString 方法
         * */
        System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss"));
        // EE 表示周几
        System.out.println(dateTime.toString("yyyy年MM月dd日 HH:mm:ss EE", Locale.ENGLISH));
        System.out.println(dateTime.toString("yyyy年MM月dd日 HH:mm:ss EE", Locale.CHINESE));


        /*
         * Str --> DateTime
         * */
        DateTimeFormatter pattern = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime strToDate = DateTime.parse("2022-07-16 15:43:42", pattern);
        System.out.println(strToDate);

        /* 执行以上代码，输出结果为：
        --------------------------------------
        2022-07-16 15:50:16
        2022年07月16日 15:50:16 Sat
        2022年07月16日 15:50:16 星期六
        2022-07-16T15:43:42.000+08:00
        ----------------------------------- */
    }

    /**
     * Part3：运算
     */
    @Test
    public void calculate(){
        DateTime now = new DateTime();
        DateTime tomorrow = now.plusDays(1);
        System.out.println(tomorrow);
        DateTime lastMonth = now.plusMonths(1);
        System.out.println(lastMonth);
        Days days = Days.daysBetween(tomorrow, lastMonth);
        System.out.println(days);

        /* 执行以上代码，输出结果为：
        --------------------------------------
        2022-07-17T15:55:54.498+08:00
        2022-08-16T15:55:54.498+08:00
        P30D
        ----------------------------------- */
    }

    /**
     * 获取值
     */
    @Test
    public void get(){
        DateTime now = new DateTime();
        int dayOfWeek = now.getDayOfWeek();
        int day = now.getDayOfMonth();
        int year = now.getYear();
        int month = now.getMonthOfYear();

        // 判断是否为闰年（闰年要多一个月）
        DateTime.Property property = now.monthOfYear();
        System.out.println(property.getAsText());
        System.out.println(property.getAsText(Locale.CHINA));
        System.out.println(property.getAsShortText());
        System.out.println(property.getAsShortText(Locale.CHINA));
        boolean leap = now.monthOfYear().isLeap();

    }

    /**
     * 1、日期格式化
     * 2、
     *      优点（与JDK中的 SimpleFormat 相比）：
     *          1、多线程环境下线程安全
     *          2、底层实现简单，调用的栈少，执行速度快
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    public void test04(){
        // 日期格式化
        System.out.println(DATE_TIME_FORMATTER.print(new DateTime()));
        // 字符串日期 --> DateTime --> Date
        System.out.println(DATE_TIME_FORMATTER.parseDateTime("2022-07-11 12:18:31").toDate());

        /* 执行以上代码，输出结果为：
        --------------------------------------
        2022-07-16 19:52:33
        Mon Jul 11 12:18:31 CST 2022
        ----------------------------------- */
    }
}
