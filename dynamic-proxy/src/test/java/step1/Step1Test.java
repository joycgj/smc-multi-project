package step1;

import org.junit.Test;

import java.lang.reflect.Proxy;

public class Step1Test {
    @Test
    public void test() {
        Target target = new TargetImpl();
        target = (Target) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new TargetProxy(target));
        target.execute();
    }
}
