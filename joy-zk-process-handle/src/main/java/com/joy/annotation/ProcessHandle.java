package com.joy.annotation;

import com.joy.enums.NotifyTypeEnum;
import com.joy.enums.ZKNodeTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: gaojiechen
 * Date: 14-6-12
 * Time: 下午2:43
 * To change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ProcessHandle {
    ZKNodeTypeEnum value();
    NotifyTypeEnum type() default NotifyTypeEnum.SINGLE;
}
