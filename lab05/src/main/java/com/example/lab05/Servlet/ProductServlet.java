package com.example.lab05.Servlet;


import com.example.lab05.DAO.ProductDAO;
import com.example.lab05.Model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "product", value = "/product")
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;

    public void init() {
        productDAO = new ProductDAO();
        List<Product> products = productDAO.getAll();
        getServletContext().setAttribute("products", products);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        request.setAttribute("username", username);
        List<Product> products = productDAO.getAll();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        String action = request.getParameter("action");
        if (action != null && action.equals("delete")) {
            long productId = Integer.parseInt(request.getParameter("id"));
            productDAO.delete(productId);
        } else if (name == null || name.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Vui lòng nhập tên sản phẩm");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            int price = Integer.parseInt(request.getParameter("price"));
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            productDAO.create(product);
        }
        response.sendRedirect(request.getContextPath() + "/product");
    }


}
