package org.fenixsoft.clazz;

public class SpinTest2 {
    void dspin() {
        double i;
        for (i = 0.0; i < 100.0; i++) {
            ; // Loop body is empty } }
        }
    }

    double doubleLocals(double d1, double d2) {
        return d1 + d2;
    }

    int align2grain(int i, int grain) {
        return ((i + grain - 1) & ~(grain - 1));
    }

    void useManyNumeric() {
        int i = 100;
        int j = 1000000;
        long l1 = 1;
        long l2 = 0xffffffff;
        double d = 2.2;
    }

    void whileInt() {
        int i = 0;
        while (i < 100) {
            i++;
        }
    }

    int addTwo(int i, int j) {
        return i + j;
    }

    static int addTwoStatic(int i, int j) {
        return i + j;
    }

    int add12and13() {
        return addTwo(12, 13);
    }

    int add12and13_2() {
        return addTwoStatic(12, 13);
    }
}