package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();

    List<Ad> findByTitle(String title);

    // insert a new ad and return the new ad's id
    Long insert(Ad ad);
}
