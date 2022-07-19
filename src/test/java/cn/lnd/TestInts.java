package cn.lnd;

import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Author lnd
 * @Description
 *      Ints 用于补充 Integer 和 Arrays 对 int 类型的操作
 *      常用方法：
 *          - compare
 *          - asList
 *          - contains
 *          - max/min
 *
 *          Math
 * @Date 2022/7/15 15:42
 */
public class TestInts {

    @Test
    public void compare(){
        int a = 2000000000;
        //int a = 20;
        int b = -a;
        /**
         * a 是一个正数，b是一个负数，但 a-b仍是一个负数（为什么？），变成了一个错误值。
         * 所以在 Java 中进行两个数之间大小的比较，并不能简单的使用减法操作（使用大于号小于号是可以的，因为Guava的Ints就是这么做的），
         * 而 Ints 则提供了一个比较两个数值大小的方法
         */
        boolean flag = (a - b) > 0;
        System.out.println(flag); // false

        int compare = Ints.compare(a, b);
        System.out.println(compare); // 1（注：1表示true，即 a>b）
    }


    /**
     * JDK中，并没有提供判断数组中是否包含某个元素的方法，
     * 而 Ints 中则提供了这一方法
     *
     * 源码很简单
     */
    @Test
    public void testContains(){
        System.out.println(Ints.contains(new int[]{1,2,3,4}, 3));
    }

    @Test
    public void testMaxAndMin(){
        int max = Ints.max(new int[]{1, 3, 5, 6, 7});
        System.out.println(max);
        int min = Ints.min(new int[]{1, 3, 5, 6, 7});
        System.out.println(min);
    }

    /**
     * JDK的Arrays中也有一个asList方法，但与 Guava 中的 asList 相比，更容易使用。
     */
    @Test
    public void testAsList(){
        int[] array = {1, 2, 3};
        /*
        * JDK 中的 asList，虽然返回的是一个 List 对象，但List中元素的类型是一个 int[] 数组，
        * 而我们一般想要的是 List 中的元素是一个个 Integer 类型的对象，而 Guava 做到了这一点
        * */
        List<int[]> jdkInts = Arrays.asList(array);
        // List<int[]> 和 List<Integer>
        List<Integer> guavaInts = Ints.asList(array);

        System.out.println(jdkInts); // [[I@41975e01]
        System.out.println(guavaInts); // [1, 2, 3]

        /*
        * 如果我们想使用 JDK 方法得到List中数组的元素，还需要额外的步骤
        * */
        int[] ints = jdkInts.get(0);
        String s = Arrays.toString(ints);
        System.out.println(s); // [1, 2, 3]

        /*
        * 在 Guava 中，通过 asList 得到的 List 是一个不可变的 List 对象。
        * 通过查看源码可知，该对象并没有重写 add 方法，即不能完成添加操作
        * */

        //guavaInts.add(90); // 运行该行代码会抛出异常。
        /* java.lang.UnsupportedOperationException*/

    }
}
