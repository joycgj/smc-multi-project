package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedInputFile {
    public static String read(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null) {
            sb.append(s + "\n");
        }
        in.close();
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.print(read("D:\\idea_workspace\\smc-multi-project\\joy-thinking-in-java-4th\\src\\main\\java\\io\\BufferedInputFile.java"));
    }
}
