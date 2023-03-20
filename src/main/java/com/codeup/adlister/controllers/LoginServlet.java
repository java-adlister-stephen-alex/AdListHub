package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.Ads;
import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.dao.MySQLAdsDao;
import com.codeup.adlister.dao.Users;
import com.codeup.adlister.models.User;
import org.mindrot.jbcrypt.BCrypt;
import util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

@WebServlet(name = "controllers.LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if the username currently in does not match the sticky, then remove the attribute
        String stickyFromRegister = (String) request.getSession().getAttribute("stickyUsernameRegister");

        if(request.getSession().getAttribute("stickyUsernameRegister") == stickyFromRegister) {
            request.getSession().setAttribute("stickyFromRegister", stickyFromRegister);
        }

        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/profile");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().removeAttribute("stickyUsernameRegister");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean passwordsMatch = false;

        User user = DaoFactory.getUsersDao().findByUsername(username);

        request.getSession().setAttribute("stickyUsernameRegister", username);

        if (user == null) {
            response.sendRedirect("/login");
            return;
        }

//        checking the hash
        boolean hashOk = Password.check(password, Password.hash(password));
        System.out.println("Hash ok?: " + hashOk);

        if (Password.check(password, user.getPassword())) {
            passwordsMatch = true;
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("userId", user.getId());
            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/login");
        }
    }
}
