package cn.lnd.collections;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;

/**
 * @Author lnd
 * @Description
 * @Date 2022/7/19 14:05
 */
public class SetsDemo {

    Set<Integer> ints = Sets.newHashSet(1,2,3);

    /**
     * {@link Sets#filter(Set, Predicate)}
     *      作用：过滤 set 集合中的元素
     *      注意：filter方法的结果是 {@link Predicate#apply(Object)} 返回 true 的元素组成的集合
     *
     */
    @Test
    public void filter(){
        Set<Integer> set = Sets.filter(ints, (e) -> {
            return e > 1;
        });
        System.out.println(set);

        /* 执行以上代码，输出结果为：
        --------------------------------------
        [2, 3]
        ----------------------------------- */
    }
}
