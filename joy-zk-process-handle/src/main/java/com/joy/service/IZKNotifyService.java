package com.joy.service;

import com.joy.enums.NotifyTypeEnum;
import com.joy.enums.ZKNodeTypeEnum;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: gaojiechen
 * Date: 14-6-19
 * Time: 下午6:20
 * To change this template use File | Settings | File Templates.
 */
public interface IZKNotifyService {

    /**
     * 服务对zk监控，对其变化进行清理缓存
     * @param object
     * @param zkNode
     * @param type
     * @param method
     */
    public void initWatchZKNodeUpdateCache(final Object object, final ZKNodeTypeEnum zkNode, final NotifyTypeEnum type, final Method method);

    /**
     * 设置zkNode的值
     * @param key
     * @param value
     * @return
     */
    public boolean setZKNodeValue(String key, String value);

    /**
     * 设置zkNode的值
     * @param code
     * @param queryString
     * @return
     */
    public boolean setZKNode(String code, String queryString);
}
