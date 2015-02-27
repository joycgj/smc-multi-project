package org.fenixsoft.classloading;

/**
 * Created by gaojiechen on 2015/2/13.
 */
public class SuperClass {
    static {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;
}
