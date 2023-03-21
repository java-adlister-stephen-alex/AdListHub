package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
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

    @Override
    public List<Ad> all() {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ads");
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public List<Ad> findByTitle(String title) {
        System.out.println(title);
        String findQry = "SELECT * FROM ads WHERE title LIKE ?";
        try {
            PreparedStatement statement = connection.prepareStatement(findQry);
            statement.setString(1,  "%" + title + "%");
            ResultSet rs = statement.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error at findByTitle method in MySQLAdsDao", e.getCause());
        }
    }

    @Override
    public List<Ad> findById(String id) {
        System.out.println(id);
        String findQry = "SELECT * FROM ads WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(findQry);
            statement.setString(1,  id);
            ResultSet rs = statement.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error at findByTitle method in MySQLAdsDao", e.getCause());
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQry = "INSERT INTO ads(user_id, title, description, price) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQry, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, ad.getUserId());
            statement.setString(2, ad.getTitle());
            statement.setString(3, ad.getDescription());
            statement.setLong(4, ad.getPrice());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    private List<String> getCategoriesForAd(long id) {
        String findQry = "SELECT c.category" +
                " FROM ads a " +
                " JOIN category_ad ca ON a.id = ca.ad_id " +
                " JOIN categories c ON c.id = ca.category_id " +
                " WHERE a.id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(findQry);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            return createCategoryListFromResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Ad> getAdsByCategory(String category) {
        String findQry = " SELECT * FROM ads " +
                " JOIN category_ad ON ads.id = category_ad.ad_id " +
                " JOIN categories ON category_id = categories.id " +
                " WHERE categories.category = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(findQry);
            statement.setString(1,  category);
            ResultSet rs = statement.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        long adId = rs.getLong("id");

        List<String> adCategories = getCategoriesForAd(adId);

        return new Ad(
            adId,
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description"),
            rs.getLong("price"),
            adCategories
        );
    }

    private List<String> createCategoryListFromResults(ResultSet rs) throws SQLException {
        List<String> categories = new ArrayList<>();
        while (rs.next()) {
            categories.add(rs.getString(1));
        }
        return categories;
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }
}
