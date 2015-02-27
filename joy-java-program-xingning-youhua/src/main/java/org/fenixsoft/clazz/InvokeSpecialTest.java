package org.fenixsoft.clazz;

public class InvokeSpecialTest {
    Object create() { return new Object(); }
}

class Near {
    int it;

    public int getItNear() {
        return getIt();
    }

    private int getIt() {
        return it;
    }
}

class Far extends Near {
    int getItFar() {
        return super.getItNear();
    }
}
