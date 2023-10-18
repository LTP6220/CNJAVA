package com.example.lab05.Servlet;

import com.example.lab05.DAO.UserDAO;
import com.example.lab05.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getCookies().length);
        for (Cookie cookie : request.getCookies()) {
            System.out.println(cookie.getName());
            if (cookie.getName().equals("username")) {

                request.setAttribute("username", cookie.getValue());
                response.sendRedirect(request.getContextPath() + "/product");
                return;
            }
        }
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByUsername(username);

        if (user == null || !user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("flash_message", "Invalid username or password");
            System.out.println("No login yet");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            Cookie usernameCookie = new Cookie("username", username);
            usernameCookie.setMaxAge(30 * 24 * 60 * 60);
            response.addCookie(usernameCookie);


            response.sendRedirect(request.getContextPath() + "/product");
        }
    }
}