package org.fenixsoft.clazz;

/**
 * Created by gaojiechen on 2015/2/14.
 */
public class ExecuteTest {
    public void f1() {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.gc();
    }
}
