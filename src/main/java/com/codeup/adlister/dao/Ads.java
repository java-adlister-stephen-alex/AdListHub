package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;

import java.util.List;

public interface Ads {

    boolean patchById(String inputId, String title, String description, Long price);

    boolean deleteById(String id);

    // get a list of all the ads
    List<Ad> all();

    List<Ad> findByTitle(String title);

    // insert a new ad and return the new ad's id
    Long insert(Ad ad);

    List<Ad> findById(String id);

    List<Category> getCategoriesForAd(Long id);
}
