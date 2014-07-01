package com.joy.processor;

import com.joy.annotation.ProcessHandle;
import com.joy.service.IZKNotifyService;
import com.joy.service.ServiceFactory;
import org.apache.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: gaojiechen
 * Date: 14-6-28
 * Time: 下午12:19
 * To change this template use File | Settings | File Templates.
 */
public class InitNotifyProcessor {
    private static Logger logger = Logger.getLogger(InitNotifyProcessor.class);

    private static IZKNotifyService notifyService = ServiceFactory.getZkNotifyService();

    public static void initNotify(Object bean) {
        if (bean == null) {
            logger.error("[InitNotifyProcessor.initNotify]:Bean is null.");
            return;
        }

        try {
            for (Method method : bean.getClass().getDeclaredMethods()) {
                for (Annotation methodAnnotation : method.getAnnotations()) {
                    if (methodAnnotation.annotationType().equals(ProcessHandle.class.getName())) {
                        ProcessHandle handle = method.getAnnotation(ProcessHandle.class);
                        notifyService.initWatchZKNodeUpdateCache(bean, handle.value(), handle.type(), method);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("[InitNotifyProcessor.initNotify]:Throws Exception.bean="+bean.getClass().getName(), e);
        }
    }
}
