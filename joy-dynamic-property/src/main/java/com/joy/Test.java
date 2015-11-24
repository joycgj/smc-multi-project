package com.joy;

//import com.netflix.config.ConfigurationManager;
//import com.netflix.config.DynamicPropertyFactory;
//import com.netflix.config.DynamicWatchedConfiguration;
import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.netflix.config.DynamicWatchedConfiguration;
import com.netflix.config.source.ZooKeeperConfigurationSource;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

public class Test {
    public static DynamicStringProperty withdraw = DynamicPropertyFactory.getInstance().getStringProperty("withdraw", "<none>");

    static {
        try {
            String zkConfigRootPath = "/zk/conf";

            CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1",
                    new ExponentialBackoffRetry(1000, 3));
            client.start();

            ZooKeeperConfigurationSource zkConfigSource = new ZooKeeperConfigurationSource(client, zkConfigRootPath);
            zkConfigSource.start();

            DynamicWatchedConfiguration zkDynamicConfig = new DynamicWatchedConfiguration(zkConfigSource);

            ConfigurationManager.install(zkDynamicConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        while (true) {

            System.out.println(withdraw.get());
//
//            myProperty = DynamicPropertyFactory.getInstance().getStringProperty("peter", "<none>").get();
//            System.out.println(myProperty);
            TimeUnit.SECONDS.sleep(5);
        }
    }
}

//class Constant {
//    public static int withdrawuplimit = (DynamicPropertyFactory.getInstance().getIntProperty("withdrawuplimit", "<none>").get();
//}
