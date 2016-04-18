package step2;

import org.junit.Test;

public class Step2Test {

    @Test
    public void test() {
        Target target = new TargetImpl();
        target = (Target) TargetProxy.bind(target);
        target.execute();
    }
}
