package cn.lnd;

import com.google.common.base.CharMatcher;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;

/**
 * @Author lnd
 * @Description
 * @Date 2022/7/16 11:14
 */
public class TestObjects {

    /**
     * 为避免 JDK 中 equals 方法可能出现的空指针异常，Guava 通过 Objects 工具类实现了自己的 equals 方法。
     */
    @Test
    public void equalsNullable(){
        String a = null;
        String b = "2";
        //System.out.println(a.equals(b));  // NullPointerException
        System.out.println(Objects.equal(a, b));
    }

    /**
     * 接收一个可变参数对象（Object... objects），生成对应的Hash值
     */
    @Test
    public void hash(){
        System.out.println(Objects.hashCode(1,3));
    }

    /**
     * 返回两个参对象第一个不为 null 的对象
     */
    @Test
    public void firstNonNull(){
        System.out.println(MoreObjects.firstNonNull(null, "a")); // a
        System.out.println(MoreObjects.firstNonNull("b", null)); // b
    }

    // TODO 将这段代码添加到博客中
    /**
     * 额外测试一个 CharMatcher 和 Splitter 的配合使用
     */
    @Test
    public void guavaSplit(){
        String toSplit = "ab1a,a21";
        //String toSplit = "aba21a,a21";
        List<String> list = Splitter.on(",")
                .trimResults(CharMatcher.anyOf("a1"))
                .splitToList(toSplit);
        for (String s : list) {
            System.out.println(s); //
        }
        /* 执行以上代码，输出结果为：
        --------------------------------------
        ab1a,a2 ==> b  2
        aba21a,a21 ==> ba2  2
        -----------------------------------
        这说明，trimResults 方法只对每部分字符的开头和结尾字符进行处理。
        注意：如果在字符串开头或结尾的部分出现连续匹配成功，则会把匹配成功的都去除掉，直到遇见第一个匹配失败的字符
        */
    }
}
