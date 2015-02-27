package io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class FormattedMemoryInput {
    public static void main(String[] args) throws IOException {
        String s = "D:\\idea_workspace\\smc-multi-project\\joy-thinking-in-java-4th\\src\\main\\java\\io\\BufferedInputFile.java";
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(BufferedInputFile.read(s).getBytes()));
        while (in.available() != 0) {
            System.out.print((char) in.readByte());
        }
    }
}
