package com.joy;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 * User: gaojiechen
 * Date: 14-7-4
 * Time: 下午2:53
 * To change this template use File | Settings | File Templates.
 */
public class KestrelTest {

    private final static String QUEUE_NAME = "KQ";

    private ApplicationContext app;
    private MemcachedClient memcachedClient;

    @Before
    public void before() {
        app = new ClassPathXmlApplicationContext("applicationContext.xml");
        memcachedClient = (MemcachedClient) app.getBean("memcachedClient");
    }

    @Test
    public void send() {
        for (int i = 0; i < 100; i++) {
            try {
                memcachedClient.set(QUEUE_NAME, 0, i);
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (MemcachedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void receive() {
        Integer value;
        try {
            while (true) {
                value = (Integer) memcachedClient.get(QUEUE_NAME);
                if (value == null) {
                    break;
                }
                System.out.println(value);
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
    }
}
