package com.example.Ex1;


import lombok.Data;

@Data
public class Contact {

    private String name;
    private String email;
    private String message;

    // getters and setters

    public Contact() {
    }

    public Contact(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }
}