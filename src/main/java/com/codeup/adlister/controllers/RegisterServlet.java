package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.dao.Users;
import com.codeup.adlister.models.User;
import util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: show the registration form
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        User nameUnique = DaoFactory.getUsersDao().findByUsername(username);
        User emailUnique = DaoFactory.getUsersDao().findByEmail(email);
        boolean invalidInput = username.isEmpty() || email.isEmpty() || password.isEmpty() || (!password.equals(confirmPassword)) || nameUnique != null || emailUnique != null;

        request.getSession().setAttribute("stickyUsernameRegister", username);
        request.getSession().setAttribute("stickyEmail", email);

        if(invalidInput) {
            request.getSession().setAttribute("usernameEmailExists", true);
            response.sendRedirect("/register");
            return;
        }

        password = Password.hash(password);
        User user = new User(1, username, email, password);
        Users dao = DaoFactory.getUsersDao();
        dao.insert(user);

        response.sendRedirect("/login");
    }
}
