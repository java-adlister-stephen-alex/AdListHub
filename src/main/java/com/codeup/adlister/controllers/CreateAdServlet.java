package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("intendedRedirectCreateAd", true);
            response.sendRedirect("/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp")
            .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String price = request.getParameter("price");
        long priceLong;
        try {
            priceLong = Long.parseLong(price);
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Error parsing long");
            request.getSession().setAttribute("priceValidation", true);
            response.sendRedirect("/ads/create");
            return;
        }
        request.setAttribute("price", priceLong);

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect("/login");
            return;
        }

        //figure out how to get user selected categories into the Ad object

        //ad object has list of all categories that need to be added to the Ad

        List<Category> adCategories = new ArrayList<>();
        List<Category> allCategories = DaoFactory.getCategoriesDao().all();
        System.out.println(allCategories);
        for (Category category : allCategories) {
            if (category.getCategory().contains(" ")){
                System.out.println(category.getCategory().replaceAll(" ", ""));
                System.out.println(request.getParameter(category.getCategory().replaceAll(" ", "")));
                if(category.getCategory().replaceAll(" ", "").equals(request.getParameter(category.getCategory().replaceAll(" ", "")))){
                    adCategories.add(category);
                    System.out.println(adCategories.size() + ": ad categories");
                }
            } else {
                if(category.getCategory().equals(request.getParameter(category.getCategory()))){
                    adCategories.add(category);
                    System.out.println(adCategories.size() + ": ad categories");
                }
            }

        }


        Ad ad = new Ad(
            user.getId(),
            request.getParameter("title"),
            request.getParameter("description"),
            priceLong
        );
        DaoFactory.getAdsDao().insert(ad);
        Ad adWithId = DaoFactory.getAdsDao().findByTitle(request.getParameter("title")).get(0);
        if(adCategories.size() > 0){
            for (Category adCategory : adCategories) {
                System.out.println(adCategory + ": ad categories if not null");
                DaoFactory.getCategoriesAdsDao().insert(
                        adWithId.getId(),
                        adCategory.getId()
                );
            }
        }


//        DaoFactory.getCategoriesAdsDao().insert(ad)
        response.sendRedirect("/ads");
    }
}
