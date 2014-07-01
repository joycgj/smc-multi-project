package com.joy.service;

import com.joy.conf.SysConfig;
import com.joy.processor.InitNotifyProcessor;
import com.joy.zookeeper.ZooKeeperOperator;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: gaojiechen
 * Date: 14-6-12
 * Time: 下午3:04
 * To change this template use File | Settings | File Templates.
 */
public class ServiceFactory {
    private static Logger logger = Logger.getLogger(ServiceFactory.class);

    private final static IChannelService channelService;

    private final static IZKNotifyService zkNotifyService;

    private static ZooKeeperOperator zooKeeperOperator;

    static {
        channelService = new ChannelServiceImpl();
        zkNotifyService = new ZKNotifyServiceImpl();

        try {
            zooKeeperOperator = new ZooKeeperOperator(SysConfig.ZkAddress);
        } catch (Exception e) {
            logger.error("[ServiceFactory.static{}]:Init zooKeeperOperator throws exception.", e);
        }

        // 初始化通知服务
        InitNotifyProcessor.initNotify(channelService);
        InitNotifyProcessor.initNotify(zkNotifyService);
    }

    public static ZooKeeperOperator getZooKeeperOperator() {
        return zooKeeperOperator;
    }

    public static IZKNotifyService getZkNotifyService() {
        return zkNotifyService;
    }

    public static IChannelService getChannelService() {
        return channelService;
    }
}
