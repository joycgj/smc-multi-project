package cn.javass.dp.singleton.example3;

/**
 * 单例示例--饿汉式
 */
public class Singleton {
    /**
     * 定义一个变量来存储创建好的类实例，直接在这里创建类实例，只会创建一次
     */
    private static Singleton uniqueInstance = new Singleton();

    /**
     * 私有化构造方法，好在内部控制创建实例的数目
     */
    private Singleton() {
    }

    /**
     * 定义一个方法来为客户端提供类实例
     *
     * @return 一个Singleton的实例
     */
    public static Singleton getInstance() {
        //直接使用已经创建好的实例
        return uniqueInstance;
    }
}
