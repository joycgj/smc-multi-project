package com.phei.netty.aio;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * Created by chengaojie on 15/9/26.
 */
public class AsyncTimeClientHandler implements CompletionHandler<Void, AsyncTimeClientHandler>, Runnable {

    private AsynchronousSocketChannel client;
    private String host;
    private int port;
    private CountDownLatch latch;

    public AsyncTimeClientHandler(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            client = AsynchronousSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }

    @Override
    public void completed(Void result, AsyncTimeClientHandler attachment) {

    }

    @Override
    public void failed(Throwable exc, AsyncTimeClientHandler attachment) {

    }
}
