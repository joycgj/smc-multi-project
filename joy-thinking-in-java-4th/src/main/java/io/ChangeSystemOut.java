package io;

import java.io.PrintWriter;

/**
 * Created by gaojiechen on 2015/2/27.
 */
public class ChangeSystemOut {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out, true);
        out.println("Hello, world");
    }
}
