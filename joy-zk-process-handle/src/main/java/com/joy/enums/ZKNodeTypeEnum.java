package com.joy.enums;

/**
 * Created with IntelliJ IDEA.
 * User: gaojiechen
 * Date: 14-6-12
 * Time: 下午2:46
 * To change this template use File | Settings | File Templates.
 * http://221.179.173.197/api/function/notify.go?code=channelNews&action=DEL&channelId=9&data=573344:14152432
 */
public enum ZKNodeTypeEnum {
    /**
     * 即时新闻 *
     */
    CHANNEL_NEWS("channelNews", "/mpaper/channelNews", 2, false),;

    /**
     * code 用来标识通知的类型;比如:升级,loading;
     */
    private String code;

    /**
     * path 用来设置zookeeper的目录节点，要保证其唯一
     */
    private String path;

    /**
     * 更新的缓存的机制 0：local（本地缓存）；1：distribute（分布式）；2：all(本地缓存和分布式缓存)
     */
    private int type;

    /**
     * 是否每天过了晚上12点刷新一次缓存
     */
    private boolean refrushEveryDay;

    private ZKNodeTypeEnum(String code, String path, int type, boolean refrushEveryDay) {
        this.code = code;
        this.path = path;
        this.type = type;
        this.refrushEveryDay = refrushEveryDay;
    }

    public String getCode() {
        return code;
    }

    public String getPath() {
        return path;
    }

    public int getType() {
        return type;
    }

    public boolean isRefrushEveryDay() {
        return refrushEveryDay;
    }

    public static ZKNodeTypeEnum getZKNodeByCode(String code) {
        for (ZKNodeTypeEnum each : ZKNodeTypeEnum.values()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
