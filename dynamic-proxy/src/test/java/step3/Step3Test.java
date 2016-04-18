package step3;

import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class Step3Test {
    @Test
    public void test() {
        List<Interceptor> interceptorList = new ArrayList<>();
        interceptorList.add(new LogInterceptor());
        interceptorList.add(new TransactionInterceptor());

        Target target = new TargetImpl();
        target = (Target) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new TargetProxy(target, interceptorList));
        target.execute();
    }
}
