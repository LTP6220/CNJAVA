package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TextEditor textEditor = context.getBean(TextEditor.class);

        textEditor.input("Lam Truong Phu");
        textEditor.save("test.pdf");

    }
}