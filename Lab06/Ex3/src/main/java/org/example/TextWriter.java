package org.example;

import java.io.IOException;

public interface TextWriter {
    void write(String filename, String text) throws IOException;
}
