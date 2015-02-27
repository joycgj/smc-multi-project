package org.fenixsoft.clazz;

public class TryFinally {
    void tryFinally() {
        try {
            tryItOut();
        } finally {
            wrapItUp();
        }
    }

    void wrapItUp() {

    }

    void tryItOut() {

    }

    void onlyMe(Foo f) {
        synchronized (f) {
            doSomething();
        }
    }

    void doSomething() {}

    void test_aaload(String[] a) {
        String v = a[2];
    }

    void test_aastore(String v, String[] a) {
        a[2] = v;
    }


}

class Foo{}
