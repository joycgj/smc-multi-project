package io;

import java.io.*;

public class BasicFileOutput {
    static String file = "BasicFileOutput.out";
    static String ss = "D:\\idea_workspace\\smc-multi-project\\joy-thinking-in-java-4th\\src\\main\\java\\io\\BufferedInputFile.java";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read(ss)));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null) {
            out.println(lineCount++ + ": " + s);
        }
        out.close();
        System.out.println(BufferedInputFile.read(file));
    }
}
