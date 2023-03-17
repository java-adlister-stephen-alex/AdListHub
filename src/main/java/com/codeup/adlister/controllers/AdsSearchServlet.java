package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.dao.MySQLAdsDao;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.AdsSearchServlet", urlPatterns = "/ads/search")
public class AdsSearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        if(search == null) {
            System.out.println("Input required");
            request.setAttribute("ads", DaoFactory.getAdsDao().all());
            response.sendRedirect("/ads");
            return;
        }
        System.out.println(search);
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String search = request.getParameter("search");
        List<Ad> ads = new ArrayList<>();
        ads = DaoFactory.getAdsDao().findByTitle(search);
        if (ads == null){
            System.out.println("No matches found");
            request.setAttribute("ads", ads);
            response.sendRedirect("/ads");
            return;
        }
        System.out.println("Ad found  " + ads);
        request.setAttribute("ads", ads);
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }
}
