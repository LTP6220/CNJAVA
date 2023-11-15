package com.example.Ex1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/contact   ")
    public String contactForm() {
        return "contact";
    }

    @PostMapping("/contact")
    public String contactSubmit(@ModelAttribute Contact contact, Model model) {
        // handle form submit

        model.addAttribute("name", contact.getName());
        model.addAttribute("email", contact.getEmail());
        model.addAttribute("message", contact.getMessage());

        return "contact-success";

    }

    @GetMapping("/about")
    @ResponseBody
    public String about() {
        return "About this site";
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleUnsupportedMethod(Exception e) {
        return "error/405";
    }

    @ExceptionHandler(Exception.class)
    public String handleOtherError(Exception e) {
        return "error/500";
    }

}