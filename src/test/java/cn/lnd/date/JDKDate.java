package cn.lnd.date;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Author lnd
 * @Description
 * @Date 2022/7/16 18:15
 */
public class JDKDate {

    /**
     * JDK的 Date API 使用演示
     */
    @Test
    public void test01(){
        Date now = new Date();
        int day = now.getDay();
        int month = now.getMonth();
        int year = now.getYear();
        /*
        * 可以看到，JDK 的 Date API 提供了根据时间获取年、月、日等的方法。
        * 但是这些方法是一些废弃的方法，一般情况下我们是不建议使用这些方法的，
        * 因为这些方法很可能在未来的某个版本被彻底移除。
        * 同时，查看 doc 可得，JDK 在新版的 API 中通过 Calendar 来替换 Date API。
        * */
    }

    /**
     * JDK 的 Calendar API 使用演示
     */
    @Test
    public void test02(){
        Date now = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);

        calendar.get(Calendar.YEAR);
        calendar.get(Calendar.MONTH);
        calendar.get(Calendar.DAY_OF_MONTH);
        calendar.get(Calendar.DAY_OF_WEEK);
        // ....
    }

}
