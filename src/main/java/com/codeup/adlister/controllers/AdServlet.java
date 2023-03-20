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

import static java.lang.Long.parseLong;

@WebServlet(name = "controllers.AdServlet", urlPatterns = "/ads/card")
public class AdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("search");
        if(request.getParameter("edit") != null){
            List<Ad> ad;
            String id = request.getParameter("ad_card");
            ad = DaoFactory.getAdsDao().findById(id);
            request.setAttribute("ad", ad);
            request.setAttribute("edit", true);
            request.getRequestDispatcher("/WEB-INF/ads/ad.jsp").forward(request, response);
            return;
        }
        if(request.getParameter("delete") != null){
            request.setAttribute("delete", true);
            request.getRequestDispatcher("/WEB-INF/ads/ad.jsp").forward(request, response);
            return;
        }
        if(request.getParameter("deletefinal") != null){
            DaoFactory.getAdsDao().deleteById(request.getParameter("ad_card"));
            request.getRequestDispatcher("/WEB-INF/ads/ad.jsp").forward(request, response);
            return;
        }
        String id = request.getParameter("ad_card");
        List<Ad> ad = new ArrayList<>();
        if(request.getParameter("editfinal") != null){
            Ad newAd = new Ad();
            newAd.setDescription(request.getParameter("edit-description"));
            newAd.setPrice(parseLong(request.getParameter("edit-price")));
            newAd.setTitle(request.getParameter("edit-title"));
            DaoFactory.getAdsDao().deleteById(request.getParameter("ad_card"));
            ad.add(newAd);
            request.getRequestDispatcher("/WEB-INF/ads/ad.jsp").forward(request, response);
            return;
        }
        ad = DaoFactory.getAdsDao().findById(id);
        request.setAttribute("ad", ad);
        request.getRequestDispatcher("/WEB-INF/ads/ad.jsp").forward(request, response);
    }
}
