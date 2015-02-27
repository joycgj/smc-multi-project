package org.fenixsoft.clazz;

public class TestExc extends Exception {
    void cantBeZero(int i) throws TestExc {
        if (i == 0) {
            throw new TestExc();
        }
    }

//    void catchOne() {
//        try {
//            tryItOut();
//        } catch (TestExc e) {
//            handleExc(e);
//        }
//    }

    void catchTwo() {
        try {
            tryItOut();
        } catch (TestExc1 e) {
            handleExc(e);
        } catch (TestExc2 e) {
            handleExc(e);
        }
    }

    void tryItOut() throws TestExc1, TestExc2 {
    }

    void handleExc(TestExc e) {

    }

    void handleExc(TestExc1 e) {

    }

    void handleExc1(TestExc1 e) {

    }

    void handleExc(TestExc2 e) {

    }

    void handleExc2(TestExc2 e) {

    }

    void nestedCatch() {
        try {
            try {
                tryItOut();
            } catch (TestExc1 e) {
                handleExc1(e);
            }
        } catch (TestExc2 e) {
            handleExc2(e);
        }
    }
}

class TestExc1 extends Exception {
}

class TestExc2 extends Exception {
}
