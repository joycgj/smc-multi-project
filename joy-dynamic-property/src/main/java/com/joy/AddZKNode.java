package com.joy;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class AddZKNode {
    public static void main(String[] args) throws Exception {
        AddZKNode.addNode("joy", 6380, "online");
    }

    private static void addNode(String name, int port, String state) throws IOException,
            InterruptedException, KeeperException {
        int zkPort = 2181;
        String zkPath = "/zk/config";
        ZooKeeper zk = new ZooKeeper("127.0.0.1:" + zkPort, 50000000, null);
        try {
            if (zk.exists(zkPath, null) == null) {
                zk.create(zkPath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            zk.create(zkPath + "/" + name, "chengaojie".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } finally {
            zk.close();
        }
    }
}
