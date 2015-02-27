package org.fenixsoft.clazz;

public class MyObj {
    int i; // An instance variable

    MyObj example() {
        MyObj o = new MyObj();
        return silly(o);
    }

    MyObj silly(MyObj o) {
        if (o != null) {
            return o;
        } else {
            return o;
        }
    }

    void setIt(int value) {
        i = value;
    }

    int getIt() {
        return i;
    }

    void createBuffer() {
        int buffer[];
        int bufsz = 100;
        int value = 12;
        buffer = new int[bufsz];
        buffer[10] = value;
        value = buffer[11];
    }

    void createThreadArray() {
        Thread threads[];
        int count = 10;
        threads = new Thread[count];
        threads[0] = new Thread();
    }

    int[][][] create3DArray() {
        int grid[][][];
        grid = new int[10][5][];
        return grid;
    }

    int chooseNear(int i) {
        switch (i) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return -1;
        }
    }

    int chooseFar(int i) {
        switch (i) {
            case -100:
                return -1;
            case 0:
                return 0;
            case 100:
                return 1;
            default:
                return -1;
        }
    }

    public long nextIndex() {
        return index++;
    }

    private long index = 0;
}
