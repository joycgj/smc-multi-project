package concurrency;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.*;

import static net.mindview.util.Print.print;

class LiftOffRunner2 implements Runnable {
    private BlockingQueue<LiftOff> rockets;

    public LiftOffRunner2(BlockingQueue<LiftOff> rockets) {
        this.rockets = rockets;
    }

    public void add(LiftOff lo) throws InterruptedException {
        rockets.put(lo);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                LiftOff rocket = rockets.take();
                rocket.run(); // Use this thread
            }
        } catch (InterruptedException e) {
            print("Waking from take()");
        }
        print("Exiting LifeOffRunner");
    }
}

class LiftOffProducer implements Runnable {
    private LiftOffRunner2 runner;

    public LiftOffProducer(LiftOffRunner2 runner) {
        this.runner = runner;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                runner.add(new LiftOff(5));
            }
        } catch (InterruptedException e) {
            print("Waking from put()");
        }
        print("Exiting LiftOffProducer");
    }
}

public class E28_TestBlockingQueues2 {
    private static void getkey() {
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getkey(String message) {
        print(message);
        getkey();
    }

    private static void test(String msg, BlockingQueue<LiftOff> queue) {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        print(msg);
        LiftOffRunner2 runner = new LiftOffRunner2(queue);
        LiftOffProducer producer = new LiftOffProducer(runner);
        exec.execute(runner);
        exec.execute(producer);
        getkey("Press 'ENTER' (" + msg + ")");
        exec.shutdownNow();
        print("Finished " + msg + " test");
    }

    public static void main(String[] args) {
        test("LinkedBlockingQueue", // Unlimited size
                new LinkedBlockingQueue<LiftOff>());
        test("ArrayBlockingQueue", // Fixed size
                new ArrayBlockingQueue<LiftOff>(3));
        test("SynchronousQueue", // Size of 1
                new SynchronousQueue<LiftOff>());
    }
}
