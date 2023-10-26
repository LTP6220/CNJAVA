package org.example;

import org.springframework.beans.factory.annotation.Qualifier;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Qualifier
public class PlainTextWriter implements TextWriter {
    @Override
    public void write(String filename, String text) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(filename));

        printWriter.println(text);

        printWriter.close();
    }

}
