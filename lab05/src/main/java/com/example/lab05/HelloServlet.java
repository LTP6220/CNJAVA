package com.example.lab05;

import com.example.lab05.DAO.UserDAO;
import com.example.lab05.Model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;


    public void init() {
        try {
            message = "Hello World!";

            UserDAO userDAO = new UserDAO();
            User user = new User();
            user.setUsername("admin");
            user.setPassword("admin");
            userDAO.create(user);

        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}