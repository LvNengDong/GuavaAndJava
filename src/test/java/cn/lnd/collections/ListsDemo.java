package cn.lnd.collections;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lnd
 * @Description
 * @Date 2022/7/19 13:56
 */
public class ListsDemo {
    ArrayList<String> list = Lists.newArrayList(
            "one",
            "two",
            "three",
            "four",
            "five");

    /**
     * {@link Lists#partition(List, int)}
     *      作用：用于分割 list，将一个大的 list 分割成多个指定长度的子 list 集合
     */
    @Test
    public void partition(){
        int size = 2;
        List<List<String>> partition = Lists.partition(list, size);
        for (List<String> strings : partition) {
            System.out.println(strings);
        }

        /* 执行以上代码，输出结果为：
        --------------------------------------
        [one, two]
        [three, four]
        [five]
        ----------------------------------- */
    }
}
