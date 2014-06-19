package com.joy.service;

import com.joy.enums.NotifyTypeEnum;
import com.joy.enums.ZKNodeTypeEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.zookeeper.AsyncCallback.DataCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: chengaojie
 * Date: 14-6-19
 * Time: 下午8:49
 * To change this template use File | Settings | File Templates.
 */
public class ZKNotifyServiceImpl implements IZKNotifyService {
    private static Logger logger = Logger.getLogger(ServiceFactory.class);

    private Map<ZKNodeTypeEnum, List<NotifyBean>> distributeNotifyBeans = new HashMap<ZKNodeTypeEnum, List<NotifyBean>>();

    @Override
    public void initWatchZKNodeUpdateCache(final Object object, final ZKNodeTypeEnum zkNode, final NotifyTypeEnum type, final Method method) {
        // 如果是本地或本地加分布式缓存策略
        if (zkNode.getType() == 1 || (zkNode.getType() == 2 && type == NotifyTypeEnum.SINGLE)) {
            List<NotifyBean> beans = distributeNotifyBeans.get(zkNode);
            if (beans == null) {
                beans = new ArrayList<NotifyBean>();
            }
            beans.add(new NotifyBean(object, method));
            distributeNotifyBeans.put(zkNode, beans);
        } else {
            Stat stat = null;
            try {
                String path = zkNode.getPath();
                stat = ServiceFactory.getZooKeeperOperator().exists(path, false); // 判断zk节点
                if (stat == null) {
                    logger.error("[zkNotify-updateCache]: zkNode [" + path + "] not exist!!!");
                } else {
                    ServiceFactory.getZooKeeperOperator().addWatcherOperation(path, new DataCallback() {
                        @Override
                        public void processResult(int rs, String path, Object ctx, byte[] data, Stat stat) {
                            try {
                                if (data == null || data.length == 0) {
                                    return;
                                }
                                String queryString = new String(data);
                                Map<String, String> params = new HashMap<String, String>();
                                if (StringUtils.isNotBlank(queryString)) {
                                    String[] querys = StringUtils.split(queryString, "&");
                                    for (String query : querys) {
                                        String[] param = StringUtils.split(query, "=");
                                        if (param.length == 2) {
                                            param.put(param[0], param[1]);
                                        }
                                    }
                                }
                                method.setAccessible(true);
                                method.invoke(object, params);
                            } catch (Exception e) {
                                logger.error("[zkNotify-addWatcherOperation]: zknode exist KeeperException: "
                                        + e.getMessage() + ", path=" + path + ", data=" + data);
                            }
                        }
                    });
                }

            } catch (KeeperException e) {
                logger.error("[zkNotify-initWatchZKNodeUpdateCache]: zknode exist KeeperException: " + e.getMessage());
                e.printStackTrace();
            } catch (InterruptedException e) {
                logger.error("[zkNotify-initWatchZKNodeUpdateCache]: zknode exist InterruptedException: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean setZKNodeValue(String key, String value) {
        Stat stat = null;
        try {
            stat = ServiceFactory.getZooKeeperOperator().exists(key, false); // 判断zk节点
            if (stat == null) {
                logger.error("[zkNotify-setZKNodeValue]: zknode ["+key+"] not exist !!!");
                return false;
            }
            ServiceFactory.getZooKeeperOperator().set(key, value.getBytes());
        } catch (KeeperException e) {
            logger.error("[zkNotify-setZKNodeValue]: zknode exist KeeperException: "+e.getMessage());
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            logger.error("[zkNotify-setZKNodeValue]: zknode exist InterruptedException: "+e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean setZKNode(String code, String queryString) {
        Stat stat = null;
        ZKNodeTypeEnum node = ZKNodeTypeEnum.getZKNodeByCode(code);
        logger.info("[ZKNotifyServiceImpl.setZKNode]:Recieve CMS notify.code="+code+",queryString="+queryString);
        if (node == null) {
            logger.error("[zkNotify-setZKNode]: ZkNodeTypeEnum is not exist!code="+code);
            return false;
        }
        return false;
    }
}

class NotifyBean {
    Object object;
    Method method;

    NotifyBean(Object object, Method method) {
        this.object = object;
        this.method = method;
    }

    public void execute(Map<String, String> params) {
        try {
            this.method.setAccessible(true);
            this.method.invoke(this.object, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
