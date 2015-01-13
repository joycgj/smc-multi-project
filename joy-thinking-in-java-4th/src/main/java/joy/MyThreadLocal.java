package joy;

/**
 * Created by gaojiechen on 2015/1/12.
 */
public class MyThreadLocal {
    public static final ThreadLocal userThreadLocal = new ThreadLocal();

    public static void set(Context user) {
        System.out.println(Thread.currentThread());
        System.out.println(userThreadLocal);
        userThreadLocal.set(user);
    }

    public static void unset() {
        userThreadLocal.remove();
    }

    public static Context get() {
        return (Context) userThreadLocal.get();
    }
}
