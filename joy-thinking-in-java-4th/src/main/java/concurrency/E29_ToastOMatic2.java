package concurrency;

import javafx.beans.binding.When;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

class Toast2 {
    public enum Status {
        DRY,
        BUTTERED,
        JAMMED,
        READY {
            @Override
            public String toString() {
                return BUTTERED.toString() + " & " + JAMMED.toString();
            }
        }
    }

    private Status status = Status.DRY;
    private final int id;

    public Toast2(int idn) {
        id = idn;
    }

    public void butter() {
        status = (status == Status.DRY) ? Status.BUTTERED : Status.READY;
    }

    public void jam() {
        status = (status == Status.DRY) ? Status.JAMMED : Status.READY;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Toast " + id + ": " + status;
    }
}

class ToastQueue2 extends LinkedBlockingQueue<Toast2> {
}

class Toaster2 implements Runnable {
    private ToastQueue2 toastQueue;
    private int count;
    private Random rand = new Random(47);

    Toaster2(ToastQueue2 toastQueue) {
        this.toastQueue = toastQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
                Toast2 t = new Toast2(count++);
                print(t);
                toastQueue.put(t);
            }
        } catch (InterruptedException e) {
            print("Toaster interrupted");
        }
        print("Toaster off");
    }
}

class Butterer2 implements Runnable {
    private ToastQueue2 inQueue, butteredQueue;

    Butterer2(ToastQueue2 inQueue, ToastQueue2 butteredQueue) {
        this.inQueue = inQueue;
        this.butteredQueue = butteredQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast2 t = inQueue.take();
                t.butter();
                print(t);
                butteredQueue.put(t);
            }
        } catch (InterruptedException e) {
            print("Butterer interrupted");
        }
        print("Butterer off");
    }
}

class Jammer2 implements Runnable {
    private ToastQueue2 inQueue, jammedQueue;

    public Jammer2(ToastQueue2 inQueue, ToastQueue2 jammedQueue) {
        this.inQueue = inQueue;
        this.jammedQueue = jammedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast2 t = inQueue.take();
                t.jam();
                print(t);
                jammedQueue.put(t);
            }
        } catch (InterruptedException e) {
            print("Jammer interrupted");
        }
        print("Jammer off");
    }
}

class Eater2 implements Runnable {
    private ToastQueue2 finishedQueue;

    public Eater2(ToastQueue2 finished) {
        finishedQueue = finished;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast2 t = finishedQueue.take();
                if (t.getStatus() != Toast2.Status.READY) {
                    print(">>>> Error: " + t);
                    System.exit(1);
                } else {
                    print("Chomp! " + t);
                }
            }
        } catch (InterruptedException e) {
            print("Eater interrupted");
        }
        print("Eater off");
    }
}

class Alternator implements Runnable {
    private ToastQueue2 inQueue, out1Queue, out2Queue;
    private boolean outTo2;

    public Alternator(ToastQueue2 inQueue, ToastQueue2 out1Queue, ToastQueue2 out2Queue) {
        this.inQueue = inQueue;
        this.out1Queue = out1Queue;
        this.out2Queue = out2Queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast2 t = inQueue.take();
                if (!outTo2) {
                    out1Queue.put(t);
                } else {
                    out2Queue.put(t);
                }
                outTo2 = !outTo2;
            }
        } catch (InterruptedException e) {
            print("Alternator interrupted");
        }
        print("Alternator off");
    }
}

class Merger implements Runnable {
    private ToastQueue2 in1Queue, in2Queue, toBeButteredQueue,
            toBeJammedQueue, finishedQueue;

    public Merger(ToastQueue2 in1Queue, ToastQueue2 in2Queue, ToastQueue2 toBeButteredQueue,
                  ToastQueue2 toBeJammedQueue, ToastQueue2 finishedQueue) {
        this.in1Queue = in1Queue;
        this.in2Queue = in2Queue;
        this.toBeButteredQueue = toBeButteredQueue;
        this.toBeJammedQueue = toBeJammedQueue;
        this.finishedQueue = finishedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast2 t = null;
                while (t == null) {
                    t = in1Queue.poll(50, TimeUnit.MILLISECONDS);
                    if (t != null) {
                        break;
                    }
                    t = in2Queue.poll(50, TimeUnit.MILLISECONDS);
                }

                switch (t.getStatus()) {
                    case BUTTERED:
                        toBeJammedQueue.put(t);
                        break;
                    case JAMMED:
                        toBeButteredQueue.put(t);
                        break;
                    default:
                        finishedQueue.put(t);
                }
            }
        } catch (InterruptedException e) {
            print("Merger interrupted");
        }
        print("Merger off");
    }
}

public class E29_ToastOMatic2 {
    public static void main(String[] args) throws Exception {
        ToastQueue2
                dryQueue = new ToastQueue2(),
                butteredQueue = new ToastQueue2(),
                toBeButteredQueue = new ToastQueue2(),
                jammedQueue = new ToastQueue2(),
                toBeJammedQueue = new ToastQueue2(),
                finishedQueue = new ToastQueue2();

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster2(dryQueue));
        exec.execute(new Alternator(dryQueue, toBeButteredQueue,
                toBeJammedQueue));
        exec.execute(
                new Butterer2(toBeButteredQueue, butteredQueue));
        exec.execute(
                new Jammer2(toBeJammedQueue, jammedQueue));
        exec.execute(new Merger(butteredQueue, jammedQueue,
                toBeButteredQueue, toBeJammedQueue, finishedQueue));
        exec.execute(new Eater2(finishedQueue));

        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
