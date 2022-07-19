package cn.lnd.collections;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Author lnd
 * @Description
 * @Date 2022/7/19 14:30
 */
public class WrapIterator {
    /**
     * 只有当内部的迭代器处于偶数项的时候，才通过迭代器返回。
     * 否则我们对内部的迭代器继续迭代，以跳过奇数项。
     * 来实现我们只获取偶数项的功能。
     *
     */
    private static class EvenIterator implements Iterator<Integer> {

        /**
         * 是否为偶数
         */
        private boolean isEven = true;

        private Iterator<Integer> internalIterator;

        public EvenIterator(Iterator<Integer> iterator) {
            this.internalIterator = iterator;
        }

        @Override
        public boolean hasNext() {
            if (isEven){
                return internalIterator.hasNext();
            }else {
                if (internalIterator.hasNext()){
                    internalIterator.next(); // 跳过奇数项
                    isEven = !isEven;
                    return internalIterator.hasNext();
                }else {
                    return false;
                }
            }
        }

        @Override
        public Integer next() {
            // 没执行一次 next 方法，就会将 isEven 值取反，所以
            isEven = !isEven;
            return internalIterator.next();
        }

    }

    /**
     * 需求：
     *      1、只获取集合中下标为偶数的元素
     *      2、如果我们想要获取下标为4的整数倍的元素，该怎么做呢？
     */
    public static void main(String[] args) {
        ArrayList<Integer> ints = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        /**
         * 我们最常见的做法如下，但是这个方法有一个弊端就是，当我们需要取下标为4的整数倍上的元素时，
         * 不可避免地需要修改代码，这就违背了『开闭原则』
         */
        for (int i = 0; i < ints.size(); i++) {
            if (i % 2 == 0){
                System.out.println(ints.get(i));
            }
        }

        /**
         * 而使用
         * 这样当我们需求改变，我们需要获取下标为4的整数倍的选项时，
         * 我们只需要将迭代器再封装一层（装饰器模式），代码的修改程度较小。
         */

        EvenIterator evenIterator = new EvenIterator(new EvenIterator(ints.iterator()));
        while (evenIterator.hasNext()){
            System.out.println(evenIterator.next());
        }
        /* 执行以上代码，输出结果为：
        --------------------------------------
        1
        5
        9
        ----------------------------------- */
    }
}
