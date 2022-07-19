package cn.lnd.pojo;

/**
 * @Author lnd
 * @Description
 * @Date 2022/7/19 15:53
 */
public class User {
    private int userId;

    private String username;

    public User(int userId, String username) {
        this.userId = userId;
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
