package com.joy;

import org.apache.commons.lang.StringUtils;

/**
 * Created by gaojiechen on 2014/11/5.
 */
public enum ServerEnvEnum {
    /**
     * 线上环境
     */
    ONLINE("ONLINE"),
    /**
     * 开发环境
     */
    DEV("DEV"),
    /**
     * 测试环境
     */
    TEST("TEST"),
    /**
     * 预发布环境
     */
    PRE("PRE");

    private String code;

    ServerEnvEnum(String code) {
        this.code = code;
    }

    public static ServerEnvEnum getEnvByCode(String code) {
        ServerEnvEnum[] values = ServerEnvEnum.values();
        for (ServerEnvEnum each : values) {
            if (StringUtils.equals(each.code, code)) {
                return each;
            }
        }
        return ONLINE;
    }
}
