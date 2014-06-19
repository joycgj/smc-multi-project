package com.joy.zookeeper;

import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.CuratorFrameworkFactory;
import com.netflix.curator.framework.state.ConnectionState;
import com.netflix.curator.framework.state.ConnectionStateListener;
import com.netflix.curator.retry.RetryOneTime;
import org.apache.log4j.Logger;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created with IntelliJ IDEA.
 * User: gaojiechen
 * Date: 14-6-12
 * Time: 下午3:30
 * To change this template use File | Settings | File Templates.
 */
public class AbstractZooKeeper {
    private Logger logger = Logger.getLogger(AbstractZooKeeper.class);

    private String hosts;
    private static ZooKeeper zooKeeper = null;
    private static final int RETRY_COUNT = 10;

    public AbstractZooKeeper(String hosts) {
        this.hosts = hosts;
        final CuratorFramework client = CuratorFrameworkFactory.newClient(hosts, new RetryOneTime(3));
        client.start();
        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                try {
                    zooKeeper = client.getZookeeperClient().getZooKeeper();
                } catch (Exception e) {
                    logger.error("[AbstractZooKeeper] AbstractZooKeeper stateChanged error", e);
                    e.printStackTrace();
                }
                logger.info("[AbstractZooKeeper] zookeeper state changed : " + connectionState.toString()
                        + ", zookeeper client msg:" + zooKeeper);
            }
        });
    }

    /**
     * zookeeper客户端为空，最多可尝试10次连接
     * @return
     */
    public ZooKeeper getZooKeeper() {
        if (zooKeeper == null) {
            int i = 0;
            while (i++ < RETRY_COUNT) {
                try {
                    if (zooKeeper != null) {
                        break;
                    }
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    logger.error("[AbstractZooKeeper] getZooKeeper error", e);
                    e.printStackTrace();
                }
                logger.info("[AbstractZooKeeper] getZooKeeper retry count: " + i);
            }
        }
        return zooKeeper;
    }
}
