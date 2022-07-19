package cn.lnd;

import org.junit.Test;

/**
 * @Author lnd
 * @Description
 * @Date 2022/7/16 15:20
 */
public class TmpExam {
    @Test
    public void test01(){
        Object o1 = true ? new Integer(1) : new Double(2.0);
        Object o2 = new Integer(1);
        System.out.println(o1);
        System.out.println(o2);
    }
}
