package io;

import java.io.IOException;
import java.io.StringReader;

public class MemoryInput {
    public static void main(String[] args) throws IOException {
        StringReader in = new StringReader(BufferedInputFile.read("D:\\idea_workspace\\smc-multi-project\\joy-thinking-in-java-4th\\src\\main\\java\\io\\MemoryInput.java"));
        int c;
        while ((c = in.read()) != -1) {
            System.out.print((char)c);
        }
    }
}
