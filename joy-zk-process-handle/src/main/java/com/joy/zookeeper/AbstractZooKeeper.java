package com.joy.zookeeper;

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
    }
}
