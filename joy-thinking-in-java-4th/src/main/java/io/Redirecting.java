package io;

import java.io.*;

/**
 * Created by gaojiechen on 2015/2/27.
 */
public class Redirecting {
    public static void main(String[] args) throws IOException {
        String ss = "D:\\idea_workspace\\smc-multi-project\\joy-thinking-in-java-4th\\src\\main\\java\\io\\BufferedInputFile.java";
        PrintStream console = System.out;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(ss));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("test.out")));
        System.setIn(in);
        System.setOut(out);
        System.setErr(out);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }
        out.close();
        System.setOut(console);
    }
}
