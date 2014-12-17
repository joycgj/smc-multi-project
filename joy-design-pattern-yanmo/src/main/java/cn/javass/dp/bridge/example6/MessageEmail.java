package cn.javass.dp.bridge.example6;

/**
 * 以Email的方式发送消息
 * 24.2.4
 */
public class MessageEmail implements MessageImplementor {
    @Override
    public void send(String message, String toUser) {
        System.out.println("使用Email的方式，发送消息'" + message + "'给" + toUser);
    }
}
