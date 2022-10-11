package cn.lnd.jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author lnd
 * @Description
 * @Date 2022/10/9 18:00
 */
public class GroupingByTest {

    @Test
    public void test(){
        List<User> users = new ArrayList<>();
        users.add(new User("张三", 0));
        users.add(new User("李四", 1));
        users.add(new User("王五", 1));
        users.add(new User("赵六", 0));
        Map<Integer, List<User>> map = users.stream().collect(Collectors.groupingBy(User::getGender));
        for (Integer key : map.keySet()) {
            System.out.println("key:" + key);
            System.out.println("value：" + map.get(key));
            System.out.println("============================================");
        }
        /* 执行以上代码，输出结果为：
        --------------------------------------
            key:0
            value：[User{name='张三', gender=0}, User{name='赵六', gender=0}]
            ============================================
            key:1
            value：[User{name='李四', gender=1}, User{name='王五', gender=1}]
            ============================================
        -----------------------------------
        分析： Collectors.groupingBy() 方法类似于数据库中的 groupBy 分组。可以对List中的数据进行分组。
        得到一个Map<K,V>，其中key为分组关键字，value为该关键字对应的值
        */
    }


    @Test
    public void testMapping(){
        List<User> users = new ArrayList<>();
        users.add(new User("张三", 0));
        users.add(new User("李四", 1));
        users.add(new User("王五", 1));
        users.add(new User("赵六", 0));

        //arg1：处理规则  arg2：收集规则
        String names  = users.stream().collect(Collectors.mapping(User::getName, Collectors.joining(",", "[", "]")));
        System.out.println(names);

        /* 执行以上代码，输出结果为：
        --------------------------------------
            [张三,李四,王五,赵六]
        -----------------------------------
        分析：
        */
    }

    @Test
    public void testMapAddCollect(){
        List<User> users = new ArrayList<>();
        users.add(new User("张三", 0));
        users.add(new User("李四", 1));
        users.add(new User("王五", 1));
        users.add(new User("赵六", 0));
        String names = users.stream().map(User::getName).collect(Collectors.joining(",", "[", "]"));
        System.out.println(names);
    }

     @Test
     public void testGroupByAddMapping(){
         List<User> users = new ArrayList<>();
         users.add(new User("张三", 0));
         users.add(new User("李四", 1));
         users.add(new User("王五", 1));
         users.add(new User("赵六", 0));
         Map<Integer, String> nameByGenderMap = users.stream().collect(Collectors.groupingBy(User::getGender, Collectors.mapping(User::getName,Collectors.joining(",", "[", "]"))));
         nameByGenderMap.forEach((k, v) -> System.out.println(k + ":" + v));

         /* 执行以上代码，输出结果为：
         --------------------------------------
                0:[张三,赵六]
                1:[李四,王五]
         -----------------------------------
         分析：
         */
     }
}

class User{
    private String name;
    private int gender; //0和1

    public User(String name, int gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }
}
