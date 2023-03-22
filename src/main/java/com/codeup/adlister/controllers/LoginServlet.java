package com.codeup.adlister.controllers;
import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;
import util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }

        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/profile");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().removeAttribute("stickyUsernameRegister");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = DaoFactory.getUsersDao().findByUsername(username);

        request.getSession().setAttribute("stickyUsernameRegister", username);

        if (user == null) {
            request.getSession().setAttribute("userDNE", true);
            response.sendRedirect("/login");
            return;
        }

        if (Password.check(password, user.getPassword())) {
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("userId", user.getId());

            List<Category> allCategories = DaoFactory.getCategoriesDao().all();
            request.getSession().setAttribute("categories", allCategories);

            if(request.getSession().getAttribute("intendedRedirectCreateAd") != null) {
                request.getSession().removeAttribute("intendedRedirectCreateAd");
                response.sendRedirect("/ads/create");
                return;
            }

            if(request.getSession().getAttribute("intendedRedirectFromProfile") != null) {
                request.getSession().removeAttribute("intendedRedirectFromProfile");
                response.sendRedirect("/profile");
                return;
            }

            response.sendRedirect("/profile");
            } else {
            request.getSession().removeAttribute("userDNE");
            request.getSession().setAttribute("passwordIncorrect", true);
            response.sendRedirect("/login");
        }
    }
}
