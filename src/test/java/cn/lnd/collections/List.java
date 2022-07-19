package cn.lnd.collections;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;

/**
 * @Author lnd
 * @Description
 * @Date 2022/7/19 9:52
 */
public class List {

    /**
     * 使用 Guava 中的 {@link Lists#newArrayList} 方法创建数组
     */
    @Test
    public void test() {
        ArrayList<Object> list = Lists.newArrayList();
    }

    /**
     * 在遍历集合的时候，如果向集合中插入元素，程序会抛出异常。
     */
    @Test
    public void test2() {
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        for (Integer e : list) {
            System.out.println(e);
            //list.add(999);  // 抛出 ConcurrentModificationException（并发修改异常）
        }
        /**
         * 所以我们尽量使用不可变集合，减少多线程环境下发生异常的概率
         */
    }

    /**
     * 如果我们想要在遍历的同时修改集合中的元素，可以使用 {@link ArrayList#listIterator()} 方法
     */
    @Test
    public void test3() {
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);

        for (ListIterator<Integer> listIterator = list.listIterator(); listIterator.hasNext(); ) {
            Integer e = listIterator.next();
            System.out.println(e);
            listIterator.remove();
        }
        System.out.println(list);

        /* 执行以上代码，输出结果为：
        --------------------------------------
        1
        2
        3
        4
        5
        []
        ----------------------------------- */
    }

    @Test
    public void test4() {
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        // 洗牌
        Collections.shuffle(list);
        // 排序（不使用默认排序，自定义排序规则）
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        System.out.println(list);   // [5, 4, 3, 2, 1]

        /**
         * 当然，在 sort 方法中重写 Comparator 并不是一种优雅的写法，Guava 为我们提供了更优雅的写法
         */

        // 自然排序
        Ordering<Comparable> natural = Ordering.natural();
        // 倒序
        Ordering<Comparable> reverse = natural.reverse();
        Collections.sort(list, reverse);
        System.out.println(list);

        /**
         * 如果我们不想改变原来的集合，将新的排序号的序列放入一个新的集合，可以使用如下方法
         */
        java.util.List<Integer> newList = Ordering.natural().reverse().sortedCopy(list);
    }

    /**
     * 对于一个字符串集合：
     * 1、首先按照字符串的长度来排序
     * 2、对于长度相等的，按首字母排序
     */
    @Test
    public void test5() {
        ArrayList<String> strings = Lists.newArrayList(
                "first",
                "second",
                "third",
                "eleven",
                "file"
        );

        /**
         * 使用 JDK 原生 API 实现
         */
        Collections.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    // 按首字母排序
                    o2.compareTo(o1);
                }
                // 先按字符串长度排序
                return o2.length() - o1.length();
            }
        });
        System.out.println(strings);

        /**
         * 使用 Guava 中的 Ordering 实现
         *  onResultOf 方法的作用是：把经过上一步比较得到的元素使用新的规则继续排序
         */
        Collections.sort(strings, Ordering.natural().reverse().onResultOf(new Function<String, Integer>() {
                            @Nullable
                            @Override
                            public Integer apply(@Nullable String input) {
                                return input == null ? 0 : input.length();
                            }
                        })
                        // compound 方法的含义是当第一个参数相同时调用这个方法
                        .compound(Ordering.natural())
        );
        System.out.println(strings);
    }


    @Test
    public void test6() {

    }
}
