package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCategoriesDao implements Categories {

    private Connection connection = null;

    public MySQLCategoriesDao(Config config) {
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

    public List<Category> all() {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM categories");
            return createCategoriesFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    private List<Category> createCategoriesFromResults(ResultSet rs) throws SQLException {
        List<Category> categories = new ArrayList<>();
        while (rs.next()) {
            categories.add(extractCategory(rs));
        }
        return categories;
    }

    private Category extractCategory(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return null;
        }
        return new Category(
                rs.getLong("id"),
                rs.getString("category")
        );
    }

    public Category findByCategory(String category) {
        String findQry = "SELECT * FROM categories WHERE category = ? LIMIT 1";
        try {
            PreparedStatement statement = connection.prepareStatement(findQry);
            statement.setString(1, category);
            ResultSet rs = statement.executeQuery();
            return extractCategory(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error at findCategoryById method in SQLCategoriesDao", e);
        }
    }

    public String getCategory(Ad ad) {
        return null;
    }

//    public long getIdByCategory(String category) {
//        return null;
//    }
}
