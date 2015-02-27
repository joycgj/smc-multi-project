package org.fenixsoft.classloading;

/**
 * Created by gaojiechen on 2015/2/14.
 */
public class Test {
    static {
        i = 0;
//        System.out.println(i);
    }

    static int i = 1;

    static class Parent {
        public static int A = 1;
        static {
            System.out.println(A);
            A = 2;
            System.out.println(A);
        }
    }

    static class Sub extends Parent {
        public static int B = A;
    }

    static class DeadLoopClass {
        static {
            if (true) {
                System.out.println(Thread.currentThread() + "init DeadLoopClass");
                while (true) {}
            }
        }
    }

    public static void main(String[] args) {
        Runnable script = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "start");
                DeadLoopClass dlc = new DeadLoopClass();
                System.out.println(Thread.currentThread() + " run over");
            }
        };

        Thread thread1 = new Thread(script);
        Thread thread2 = new Thread(script);
        thread1.start();
        thread2.start();
    }
}


