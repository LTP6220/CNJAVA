package com.example.lab05.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "logout", value = "/logout")

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // remove username cookie
        for (var cookie : req.getCookies()) {
            if (cookie.getName().equals("username")) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
                resp.sendRedirect(req.getContextPath() + "/login");

                break;
            }
        }
    }
}
