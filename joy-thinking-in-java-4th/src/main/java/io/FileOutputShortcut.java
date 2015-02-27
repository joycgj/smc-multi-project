package io;

import java.io.*;

/**
 * Created by gaojiechen on 2015/2/27.
 */
public class FileOutputShortcut {
    static String file = "BasicFileOutput2.out";
    static String ss = "D:\\idea_workspace\\smc-multi-project\\joy-thinking-in-java-4th\\src\\main\\java\\io\\BufferedInputFile.java";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read(ss)));
        PrintWriter out = new PrintWriter(file);
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null) {
            out.println(lineCount++ + ": " + s);
        }
        out.close();
        System.out.println(BufferedInputFile.read(file));
    }
}
