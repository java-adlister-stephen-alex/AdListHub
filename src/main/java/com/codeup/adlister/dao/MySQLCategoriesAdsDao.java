package com.codeup.adlister.dao;
import com.mysql.cj.jdbc.Driver;

import com.codeup.adlister.models.CategoryAd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCategoriesAdsDao implements CategoriesAds{
    private Connection connection = null;

    public MySQLCategoriesAdsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    public List<CategoryAd> all() {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM category_ad");
            return createCategoriesAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all categoryAds.", e);
        }
    }

    public void insert(long adId, long categoryId) {
        try {
            String insertQry = "INSERT INTO category_ad(ad_id, category_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQry, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, adId);
            statement.setLong(2, categoryId);

            statement.executeUpdate();
//            ResultSet rs = statement.getGeneratedKeys();
//            rs.next();
//            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + "insert method error SQLCategoriesAdsDao");
        }
    }

    private List<CategoryAd> createCategoriesAdsFromResults(ResultSet rs) throws SQLException {
        List<CategoryAd> categoriesAds = new ArrayList<>();
        while (rs.next()) {
            categoriesAds.add(extractCategoryAd(rs));
        }
        return categoriesAds;
    }

    private CategoryAd extractCategoryAd(ResultSet rs) throws SQLException {
        return new CategoryAd(
                rs.getLong("ad_id"),
                rs.getLong("category_id")
        );
    }
}
