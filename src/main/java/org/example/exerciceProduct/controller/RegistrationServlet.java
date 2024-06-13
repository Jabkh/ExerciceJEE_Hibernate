package org.example.exerciceProduct.controller;

import org.example.exerciceProduct.model.User;
import org.example.exerciceProduct.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "RegistrationServlet", value = "/register")
public class RegistrationServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/auth/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        // Vérification si l'utilisateur existe déjà
        if (userService.getUserByEmail(email) != null) {
            request.setAttribute("error", "Email already exists, please choose another one.");
            request.getRequestDispatcher("WEB-INF/auth/register.jsp").forward(request, response);
            return;
        }

        // Création d'un nouvel utilisateur
        User newUser = new User(name, email, password);
        userService.addUser(newUser);

        // Redirection vers la page de connexion après l'inscription réussie
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
