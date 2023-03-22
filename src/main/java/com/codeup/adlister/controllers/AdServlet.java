package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.lang.Long.parseLong;

@WebServlet(name = "controllers.AdServlet", urlPatterns = "/ads/card")
public class AdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("search");
        if(request.getParameter("delete") != null){
            request.setAttribute("delete", true);
            request.getRequestDispatcher("/WEB-INF/ads/ad.jsp").forward(request, response);
            return;
        }
        if(request.getParameter("deletefinal") != null){
            DaoFactory.getAdsDao().deleteById(request.getParameter("ad_card"));
            request.getRequestDispatcher("/profile").forward(request, response);
            return;
        }
        List<Category> categories;
        String id = request.getParameter("ad_card");
        Long newId = parseLong(id);
        categories = DaoFactory.getAdsDao().getCategoriesForAd(newId);
        request.getSession().setAttribute("adCategories", categories);
        request.getSession().setAttribute("ad_id", id);
        List<Ad> ad;
//        if(request.getParameter("edit") != null){
//
//        }
        ad = DaoFactory.getAdsDao().findById(id);
        request.setAttribute("ad", ad);
        request.getRequestDispatcher("/WEB-INF/ads/ad.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String description = request.getParameter("edit-description");
        String price = request.getParameter("edit-price");
        long priceLong;
        String id = (String)request.getSession().getAttribute("ad_id");
        try {
            priceLong = Long.parseLong(price);
        } catch (Exception e) {
            response.sendRedirect("/ads/card?ad_card=" + id);
            return;
        }
        String title = (request.getParameter("edit-title"));
        DaoFactory.getAdsDao().patchById(id, title, description, priceLong);
        request.getSession().removeAttribute("ad_id");
        response.sendRedirect("/ads/card?ad_card=" + id);
    }
}
