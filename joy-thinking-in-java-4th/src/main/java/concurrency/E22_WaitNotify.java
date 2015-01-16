package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by gaojiechen on 2015/1/15.
 */
public class E22_WaitNotify {
    public static void main(String[] args) throws InterruptedException {
        final Runnable r1 = new Runnable() {
            @Override
            public void run() {
                for (;;) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
//                        System.out.println(this);
                        synchronized (this) {
                            System.out.println("will notify");
                            notify();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }

            @Override
            public String toString() {
                return "I am r1";
            }
        };

        final Runnable r2 = new Runnable() {
            @Override
            public void run() {
                for (;;) {
                    try {
//                        System.out.println(r1);
                        synchronized (r1) {
                            r1.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                    System.out.println("Cycled ");
                }
            }
        };

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(r1);
        exec.execute(r2);
        TimeUnit.SECONDS.sleep(10);
        exec.shutdownNow();
    }
}
