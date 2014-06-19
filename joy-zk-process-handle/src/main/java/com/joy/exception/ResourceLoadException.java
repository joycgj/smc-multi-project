package com.joy.exception;

/**
 * Created with IntelliJ IDEA.
 * User: gaojiechen
 * Date: 14-6-13
 * Time: 上午10:00
 * To change this template use File | Settings | File Templates.
 */
public class ResourceLoadException extends ServerException {
    public ResourceLoadException(String message) {
        super(message);
    }

    public ResourceLoadException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
