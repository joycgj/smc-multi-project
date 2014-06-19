package com.joy.zookeeper;

import org.apache.zookeeper.AsyncCallback.DataCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.apache.zookeeper.CreateMode.*;

/**
 * Created with IntelliJ IDEA.
 * User: gaojiechen
 * Date: 14-6-12
 * Time: 下午4:25
 * To change this template use File | Settings | File Templates.
 */
public class ZooKeeperOperator extends AbstractZooKeeper {
    private static Logger logger = LoggerFactory.getLogger(ZooKeeperOperator.class);

    public ZooKeeperOperator(String hosts) {
        super(hosts);
    }

    /**
     * 创建持久态的znode,支持多层创建。注：在创建/parent/child的情况下,无/parent，无法通过
     * @param path
     * @param data
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void create(String path, byte[] data) throws KeeperException, InterruptedException {
        this.getZooKeeper().create(path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     * 获取节点的孩子信息
     * @param path
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void getChild(String path) throws KeeperException, InterruptedException {
        List<String> children = this.getZooKeeper().getChildren(path, true);
        if (children.isEmpty()) {
            logger.info("没有节点在" + path + "中.");
            return;
        } else {
            logger.info("节点" + path + "中存在的节点:\n");
            for (String child : children) {
                logger.info(child);
            }
        }
    }

    /**
     *
     * @param path
     * @return
     */
    public List<String> getChildren(String path) throws KeeperException, InterruptedException {
        List<String> children = this.getZooKeeper().getChildren(path, false);
        return children;
    }

    public byte[] getData(String path) throws KeeperException, InterruptedException {
        return this.getZooKeeper().getData(path, true, null);
    }

    public void delete(String path) throws KeeperException, InterruptedException {
        this.getZooKeeper().delete(path, -1);
    }

    public void set(String path, byte[] data) throws KeeperException, InterruptedException {
        this.getZooKeeper().setData(path, data, -1);
    }

    public Stat exists(String path, boolean watch) throws KeeperException, InterruptedException {
        return this.getZooKeeper().exists(path, watch);
    }

    public Stat exists(String path, Watcher watcher) throws KeeperException, InterruptedException {
        return this.getZooKeeper().exists(path, watcher);
    }

    public void addWatcherOperation(final String node, final DataCallback stat) {
        try {
            this.getZooKeeper().exists(node, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getType() != Event.EventType.NodeDeleted) {
                        getZooKeeper().getData(node, this, stat, null); // 反复的监控
                        logger.info("[ZooKeeperOperator] rewatch node :["+node+"], WatchedEvent:["+event.getType()+"]");
                    } else {
                        logger.info("[ZooKeeperOperator] delete node :["+node+"], WatchedEvent:["+event.getType()+"]");
                    }
                }
            });
        } catch (KeeperException e) {
            logger.error("[ZooKeeperOperator] KeeperException", e);
            e.printStackTrace();
        } catch (InterruptedException e) {
            logger.error("[ZooKeeperOperator] InterruptedException", e);
            e.printStackTrace();
        }
    }

    public void create(String path, byte[] data, final DataCallback stat) throws KeeperException, InterruptedException {
        this.getZooKeeper().create(path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        addWatcherOperation(path, stat);
    }
}
