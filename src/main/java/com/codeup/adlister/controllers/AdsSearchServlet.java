package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.AdsSearchServlet", urlPatterns = "/ads/search")
public class AdsSearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        List<Ad> allAds = DaoFactory.getAdsDao().all();
        if(search == null){
            System.out.println("Input required");
            request.setAttribute("ads", allAds);
            request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);

        }
        List<Ad> matchedSearches = null;
        try{
            for (Ad ad : allAds) {
                if(ad.getTitle().contains(search)){
                    matchedSearches.add(ad);
                }
            }
        } catch (NullPointerException e){
            throw new ServletException("Runtime exception occured", e);
        }

        if(search != null) {

            request.setAttribute("ads", matchedSearches);
            request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
}
