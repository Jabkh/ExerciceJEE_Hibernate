package org.example.exerciceProduct.controller;

import org.example.exerciceProduct.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateUserServlet", urlPatterns = "/createUser")
public class CreateUserServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        boolean created = userService.createUser(email, name, password);

        if (created) {
            response.sendRedirect(request.getContextPath() + "/users.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
