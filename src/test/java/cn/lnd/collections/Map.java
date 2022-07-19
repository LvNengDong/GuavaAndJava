package cn.lnd.collections;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;

/**
 * @Author lnd
 * @Description
 * @Date 2022/7/19 11:24
 */
public class Map {

    /**
     * 为了避免向 HashMap 中插入多个元素时 HashMap 频繁扩容的问题，
     * Guava 提供了 {@link Maps#newHashMapWithExpectedSize(int)} 方法，
     * 用于创建一个指定容量的 HashMap，这样在 HashMap 的容量到达这个设定值之前HashMap不会发生频繁扩容
     */
    @Test
    public void test(){
        // 创建数组容量为 100 的 HashMap
        HashMap<Object, Object> hashMap = Maps.newHashMapWithExpectedSize(100);
    }
}
