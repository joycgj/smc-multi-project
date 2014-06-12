package com.joy.conf;

/**
 * Created with IntelliJ IDEA.
 * User: gaojiechen
 * Date: 14-6-12
 * Time: 下午3:10
 * To change this template use File | Settings | File Templates.
 */
public class SysConfig {

    private static String DEFAULT_FILE_NAME = "resources.properties";
    private static PropertyBundle bundle = new PropertyBundle(DEFAULT_FILE_NAME);

    public static String ZkAddress = getString("zk.server");

    /**
     * 获取key对应的字符串值
     * @param key
     * @return
     */
    public static String getString(String key) {
        return bundle.getProperty(key);
    }
}
