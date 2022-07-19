package cn.lnd.collections;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @Author lnd
 * @Description
 * @Date 2022/7/19 14:15
 */
public class MapsDemo {

    /**
     * {@link Maps#uniqueIndex(Iterable, Function)}
     *      作用：根据第一个入参对象中的一个唯一属性构建属性与对象一一对应的Map集合。
     *      使用场景：
     *          1、从数据库中读取一批数据，根据数据的某个唯一索引，建立索引与数据的映射关系。
     */
    @Test
    public void uniqueIndex(){
        ArrayList<User> users = Lists.newArrayList(
                new User(1L, "Lucy"),
                new User(2L, "Jack")
        );
        System.out.println(users);
        /* 执行以上代码，输出结果为：
        --------------------------------------
        [User{userId=1, username='Lucy'}, User{userId=2, username='Jack'}]
        ----------------------------------- */


        ImmutableMap<Long, User> idUserMap = Maps.uniqueIndex(users, (user) -> {
            return user.getUserId();
        });
        System.out.println(idUserMap);
        /* 执行以上代码，输出结果为：
        --------------------------------------
        {1=User{userId=1, username='Lucy'}, 2=User{userId=2, username='Jack'}}
        ----------------------------------- */
    }
}

class User {
    private Long userId;
    private String username;

    public User(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}