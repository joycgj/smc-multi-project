package concurrency.exercise;

import java.util.concurrent.LinkedBlockingQueue;

class Car2 {
    private final int id;
    private boolean
            engine = false, driveTrain = false, wheels = false,
            exhaustSystem = false, body = false, fender = false;

    public Car2(int idn) {
        id = idn;
    }

    public Car2() {
        id = -1;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized void addEngine() {
        engine = true;
    }

    public synchronized void addDriveTrain() {
        driveTrain = true;
    }

    public synchronized void addWheels() {
        wheels = true;
    }

    public synchronized void addExhaustSystem() {
        exhaustSystem = true;
    }

    public synchronized void addBody() {
        body = true;
    }

    public synchronized void addFender() {
        fender = true;
    }

    public synchronized String toString() {
        return "Car " + id + " [" + " engine: " + engine
                + " driveTrain: " + driveTrain
                + " wheels: " + wheels
                + " exhaust system: " + exhaustSystem
                + " body: " + body
                + " fender: " + fender + "]";
    }
}

class CarQueue extends LinkedBlockingQueue<Car2> {
}

public class E37_CarBuilder2 {
}
