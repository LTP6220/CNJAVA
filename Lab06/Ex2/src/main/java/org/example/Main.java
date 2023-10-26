package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Load the beans from the context
        Product bean1 = context.getBean("bean1", Product.class);
        Product bean2 = context.getBean("bean2", Product.class);
        Product bean3 = context.getBean("bean3", Product.class);

        // Check if bean1 and bean2 are prototype objects
        boolean isPrototype1 = context.isPrototype("bean1");
        boolean isPrototype2 = context.isPrototype("bean2");

        // Check if bean3 is a singleton object (default scope)
        boolean isSingleton3 = context.isSingleton("bean3");

        // Print bean information
        System.out.println("Bean 1 (Prototype): " + bean1);
        System.out.println("Bean 2 (Prototype): " + bean2);
        System.out.println("Bean 3 (Singleton): " + bean3);

        // Print scope information
        System.out.println("Bean 1 is a prototype: " + isPrototype1);
        System.out.println("Bean 2 is a prototype: " + isPrototype2);
        System.out.println("Bean 3 is a singleton: " + isSingleton3);
    }
}
