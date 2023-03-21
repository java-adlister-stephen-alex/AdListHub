package com.codeup.adlister.dao;

import com.codeup.adlister.models.CategoryAd;

import java.util.List;

public interface CategoriesAds {
    List<CategoryAd> all();
    Long insert(long adId, long categoryId);
}
