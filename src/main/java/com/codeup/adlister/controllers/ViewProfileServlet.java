package com.codeup.adlister.controllers;
import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.ViewProfileServlet", urlPatterns = "/profile")
public class ViewProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }

        long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        System.out.println(userId);
        List<Ad> usersAds = new ArrayList<>();
        try{
            for (Ad ad : DaoFactory.getAdsDao().all()) {
                if(ad.getUserId() == userId){
                    usersAds.add(ad);
                }
            }
        } catch (NullPointerException e){
            throw new RuntimeException("Null exception in ViewProfileServlet: ", e);
        }

        request.setAttribute("ads", usersAds);

//        request.setAttribute("ads", DaoFactory.getAdsDao().all());

        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }
}
