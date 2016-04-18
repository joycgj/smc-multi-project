package step3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class TargetProxy implements InvocationHandler {
    private Target target;
    private List<Interceptor> interceptorList;

    public TargetProxy(Target target, List<Interceptor> interceptorList) {
        this.target = target;
        this.interceptorList = interceptorList;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        for (Interceptor interceptor : interceptorList) {
            interceptor.intercept();
        }
        return method.invoke(target, args);
    }
}