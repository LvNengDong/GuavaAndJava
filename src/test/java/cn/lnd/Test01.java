package cn.lnd;

import org.junit.Test;

/**
 * @Author lnd
 * @Description
 * @Date 2022/7/15 14:28
 */
public class Test01 {

    /**
     * 整数类型溢出问题
     */
    /**
     * 反例
     * 等号左边是 long 类型的数值，等号右边是多个 int 类型的值相乘，得到的也必然是一个 int 类型值。
     * 显然，等号右边得到的 int 类型值已经超过了 Integer 的最大返回，得到的是一个错误的值，将这个
     * 错误值转换成 long 类型当然也是一个错误的值。
     */
    @Test
    public void intOverFlow(){
        long millisOfYears = 5000 * 365 * 24 * 3600 * 1000;
        System.out.println("millisOfYears：" + millisOfYears);
    }

    /**
     * 正例
     * 想要解决这一问题，可以将等号右边第一个值设为 long 类型，这样得到的计算结果就是一个long类型的正确结果，满足数值范围。
     */
    @Test
    public void intOverFlow2(){
        long millisOfYears = 5000L * 365 * 24 * 3600 * 1000;
        System.out.println("millisOfYears：" + millisOfYears);
    }

    /**
     * 反例
     *
     * 如果将最后一个数值设为 long 类型，在本题中显然也是错误的，因为在此之前已经达到了 int 的最大取值范围，
     * 结果已经出现了错误，依然会溢出。
     */
    @Test
    public void intOverFlow3(){
        long millisOfYears = 5000 * 365 * 24 * 3600 * 1000L;
        System.out.println("millisOfYears：" + millisOfYears);
    }



}
