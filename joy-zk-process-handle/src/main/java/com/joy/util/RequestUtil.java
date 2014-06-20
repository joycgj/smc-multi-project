package com.joy.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: gaojiechen
 * Date: 14-6-20
 * Time: 上午10:35
 * To change this template use File | Settings | File Templates.
 */
public class RequestUtil {

    public static String getRequestString(HttpServletRequest request, final String name, final String defaultValue) {
        String value = request.getParameter(name);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }
}
