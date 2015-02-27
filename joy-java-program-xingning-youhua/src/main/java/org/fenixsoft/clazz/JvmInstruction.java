package org.fenixsoft.clazz;

public class JvmInstruction {
    void test_aaload(String[] a) {
        String v = a[2];
    }

    void test_aastore(String v, String[] a) {
        a[2] = v;
    }

    void test_aconst_null() {
        String s = null;
    }

    Object[] test_anewarray() {
        Object[] a = new Object[10];
        int len = a.length;
        return a;
    }

    void test_athrow() throws Exception {
        throw new Exception();
    }

    void test_baload(boolean[] a, boolean[] c) {
        boolean b = a[2];
        c[2] = b;
    }
}
