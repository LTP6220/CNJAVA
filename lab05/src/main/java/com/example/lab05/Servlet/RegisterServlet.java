package com.example.lab05.Servlet;

import com.example.lab05.DAO.UserDAO;
import com.example.lab05.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "register", value = "/register")

public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String confirm_password = request.getParameter("password-confirm");
        if (password.length() < 6) {
            request.setAttribute("errorMessage", "Password must be at least 6 characters.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        if (!confirm_password.equals(password)) {
            request.setAttribute("falsePassword", "Your passwords are not the same");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(password);

        userDAO.create(user);

        response.sendRedirect(request.getContextPath() + "/login");
    }

}
