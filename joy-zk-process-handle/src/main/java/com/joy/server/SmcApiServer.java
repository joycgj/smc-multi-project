package com.joy.server;

/**
 * Created with IntelliJ IDEA.
 * User: gaojiechen
 * Date: 14-6-28
 * Time: 下午1:49
 * To change this template use File | Settings | File Templates.
 */
public class SmcApiServer extends AbstractServer {

    public SmcApiServer(String[] anArgs) {
        super(anArgs);
    }

    public static void main(String... anArgs) throws Exception {
        new SmcApiServer(anArgs).run();

    }

    @Override
    public void init(Config config) {
        config.setMin_thread(128);
        config.setMax_thread(512);

        //初始化消息中心
//        Notify.init(config.port);
    }

}
