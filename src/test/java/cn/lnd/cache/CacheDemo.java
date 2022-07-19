package cn.lnd.cache;

import cn.lnd.pojo.User;
import com.google.common.cache.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author lnd
 * @Description
 * @Date 2022/7/19 15:49
 */
public class CacheDemo {

    public static void main(String[] args) throws ExecutionException {

        // 构建一个缓存对象
        LoadingCache<String, User> cache = CacheBuilder
                .newBuilder()
                .expireAfterAccess(30, TimeUnit.MINUTES) // 单个元素的有效时间
                .refreshAfterWrite(10, TimeUnit.MINUTES) // 缓存的刷新间隔
                /*
                * removalListener 方法用于监听缓存中元素被删除的事件。
                * 当缓存中某个元素被删除时，会触发这个监听的回调方法。
                * */
                .removalListener(new RemovalListener<String, User>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, User> notification) {
                        // TODO 监听到元素被删除后触发的事件
                        String key = notification.getKey();
                        User value = notification.getValue();
                        System.out.println(String.format("『%s：%s』已被删除", key, value));
                    }
                })
                /*
                * 获取缓存的使用情况，这是一个开关，打开这个开关就可以查看缓存的使用情况
                * */
                .recordStats()
                .build(
                        /**
                         * {@link CacheLoader#load(Object)} 方法用于编辑缓存未能命中时的处理逻辑
                         */
                        new CacheLoader<String, User>() {
                            @Override
                            public User load(String key) throws Exception {
                                // 当缓存未能命中时，去数据库中查询
                                return fetchFromDB(key);
                            }
                        });

        // 使用缓存对象
        User user = cache.get("99");
        System.out.println(user);
        cache.put("2", new User(2, "张三"));
        System.out.println(cache.get("2"));
        /* 执行以上代码，输出结果为：
        --------------------------------------
        User{userId=10, username='潘周聃'}
        User{userId=2, username='张三'}
        ----------------------------------- */

    }

    /**
     * 模拟从数据库中获取数据
     * @param key
     * @return
     */
    private static User fetchFromDB(String key) {
        return new User(10, "潘周聃");
    }
}
