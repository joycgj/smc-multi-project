package com.joy;

/**
 * Created by gaojiechen on 2014/11/5.
 */
public class RestLiClientConfig {

    /**
     * 是否使用RestLi的D2来获取调用的客户端
     */
    public static boolean restLiClientUseD2 = true;

    public static String restLiClientD2ZkAdd = "";

    public static ServerEnvEnum restLiServerEnv = ServerEnvEnum.getEnvByCode("ONLINE");

    public static String restLiClientD2ZkPath = "";

    public static String restLiServerHost = "";

    /**
     * 调用的RestLi的服务器的端口
     */
    public static int restLiServerPort = 0;

    static {
        switch (restLiServerEnv) {
            case ONLINE: {
                restLiClientD2ZkPath = "/d2/online";
                break;
            }
            case PRE: {
                restLiClientD2ZkPath = "/d2/pre";
                break;
            }
            case DEV:
            case TEST: {
                restLiClientD2ZkPath = "/d2/dev";
                break;
            }
            default:
                restLiClientD2ZkPath = "/d2/online";
        }
    }
}
