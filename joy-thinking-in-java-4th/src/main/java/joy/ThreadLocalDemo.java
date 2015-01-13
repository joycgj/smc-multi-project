package joy;

import java.util.Random;

/**
 * Created by gaojiechen on 2015/1/12.
 */
public class ThreadLocalDemo extends Thread {
    public static void main(String[] args) {
        Thread threadOne = new ThreadLocalDemo();
        threadOne.start();

        Thread threadTwo = new ThreadLocalDemo();
        threadTwo.start();
    }

    @Override
    public void run() {
        Context context = new Context();
        Random random = new Random();
        int age = random.nextInt(100);
        context.setTransactionId(String.valueOf(age));
        System.out.println("set thread [" + getName() + "] contextId to " + String.valueOf(age));

        MyThreadLocal.set(context);
        MyThreadLocal2.set(context);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new BusinessService().businessMethod();
        MyThreadLocal.unset();
    }
}
