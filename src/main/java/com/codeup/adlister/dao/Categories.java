package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Categories {
    List<Category> all();

    Category extractCategory(ResultSet rs) throws SQLException;

    Category findByCategory(String category);
}
