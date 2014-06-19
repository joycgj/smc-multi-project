package com.joy.service;

import com.joy.annotation.ProcessHandle;
import com.joy.enums.NotifyTypeEnum;
import com.joy.enums.ZKNodeTypeEnum;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gaojiechen
 * Date: 14-6-13
 * Time: 上午10:02
 * To change this template use File | Settings | File Templates.
 */
public class ChannelServiceImpl implements IChannelService {

    /**
     * 清除频道新闻相关的本地缓存
     * @param params
     */
    @ProcessHandle(value = ZKNodeTypeEnum.CHANNEL_NEWS, type = NotifyTypeEnum.MULTIPLE)
    public void refrushLocalCache(Map<String, String> params) {
        System.out.println("清除本地缓存");
    }
}
