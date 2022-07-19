package cn.lnd;

import org.junit.Test;

import javax.annotation.meta.Exclusive;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @Author lnd
 * @Description
 * @Date 2022/7/15 14:39
 */
public class Test02 {

    /**
     * 反例：
     *
     * 精度丢失问题：
     *  我们日常使用的浮点数类型，如 double、float 等均存在精度丢失问题
     */
    @Test
    public void lostAccurate(){
        double a = 0.58;
        System.out.println(new BigDecimal(a)); // 0.57999999999999996003197111349436454474925994873046875
        /*
        * 我们以为的 0.58，在计算机中其实并不完全等价于 0.58，均存在一定程度上的精度丢失。
        * 如果把缺失的精度放大一定倍数，很可能会造成重大损失，比如在这个例子中，放大100倍后数值就丢失了1.
        * */
        long b = (long) (a * 100);
        System.out.println(b); // 57
    }

    /**
     * 反例
     *
     *  为了解决数据精度丢失的问题，我们可以使用 BigDecimal 类型，
     *  但在使用时还要注意一些问题。
     *
     *  下面这段代码看似没有什么问题，但在实际运行中得到的结果仍然是一个错误值，因为
     *  “0.58” 本身就是一个不精确的值，用错误验证错误，得到的只能是错误，所以最后的结果依然是57
     */
    @Test
    public void lostAccurate2(){
        BigDecimal a = new BigDecimal(0.58);
        long b = a.multiply(new BigDecimal(100L)).longValue();
        System.out.println(b); // 57
    }

    /**
     * 正例
     *
     * BigDecimal 有两种正确的创建姿势：
     *      一种是 valueOf
     *      另一种是给构造方法传递一个表示 value 值的字符串
     *  这两种方式不同的是 valueOf 方法会去掉小数点最后补的 0，
     *
     *  还要注意的一点是，BigDecimal的 equals 方法，该方法不仅比较两个数值的大小，还会比较两个数值的精度，
     *  所以在本例中的 a 和 b 是不相等的。
     *  而 compareTo 是一个纯数值的比较（两数相减）
     *
     */
    @Test
    public void lostAccurate3(){
        BigDecimal a = BigDecimal.valueOf(0.580);
        BigDecimal b = new BigDecimal("0.580");

        System.out.println(a); // 0.58
        System.out.println(b); // 0.580
        System.out.println(a.equals(b)); // false
        System.out.println(a.compareTo(b)); // 0
    }

    /**
     * 还需要注意的一点是，bigDecimal 是无法处理『无线循环小数』的，因为这类数值本身就没有固定的精度
     */
    @Test
    public void lostAccurate4(){
        BigDecimal a = BigDecimal.valueOf(1L);
        BigDecimal b = BigDecimal.valueOf(3L);
        /*
        * 如果不对结果做精度的控制的话，会抛出如下异常
        * java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
        * */
        //BigDecimal divide = a.divide(b);

        // 进行精度控制，不抛出异常。DECIMAL64是一个精度类型的枚举对象
        BigDecimal divide = a.divide(b, MathContext.DECIMAL64);
        System.out.println(divide); // 0.3333333333333333

        /*
        * 重要：
        *   我们在开发中，如果使用这一 API，一定要注意进行异常处理，因为这并不是一个编译期异常！！！
        * */
    }

}
