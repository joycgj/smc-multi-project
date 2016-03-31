package step1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TargetProxy implements InvocationHandler {
    Target target;

    public TargetProxy(Target target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("拦截前");
        Object o = method.invoke(target, args);
        System.out.println("拦截后");
        return o;
    }
}