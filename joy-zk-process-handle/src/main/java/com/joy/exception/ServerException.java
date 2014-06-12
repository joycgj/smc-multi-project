package com.joy.exception;

/**
 * Created with IntelliJ IDEA.
 * User: gaojiechen
 * Date: 14-6-12
 * Time: 下午3:21
 * To change this template use File | Settings | File Templates.
 */
public class ServerException extends RuntimeException {

    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
