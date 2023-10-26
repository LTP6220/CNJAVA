package org.example;


import lombok.Data;

@Data
public class MyBean {
    private String title;
    private String version;

    public MyBean(String title, String version) {
        this.title = title;
        this.version = version;
    }

    // Getters and setters
}
