package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
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
    public boolean patchById(String inputId, String title, String description, Long price) {
        String qry = "UPDATE ads SET title = ?, description = ?, price = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(qry);
            stmt.setString(1,   title);
            stmt.setString(2,   description);
            stmt.setLong(3,   price);
            stmt.setString(4,   inputId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error editing ad at patchById", e);
        }
    }

    @Override
    public boolean deleteById(String id) {
        String qry = "DELETE FROM ads WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(qry);
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting ad.", e);
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
            Long adId = rs.getLong(1);
            return adId;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }


    private Ad extractAd(ResultSet rs) throws SQLException {
        long adId = rs.getLong("id");

        List<Category> adCategories = getCategoriesForAd(adId);

        return new Ad(
            adId,
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description"),
            rs.getLong("price"),
            adCategories
        );
    }

    @Override
    public List<Category> getCategoriesForAd(Long id) {
        String findQry = "SELECT *" +
                " FROM ads a " +
                " JOIN category_ad ca ON a.id = ca.ad_id " +
                " JOIN categories c ON c.id = ca.category_id " +
                " WHERE a.id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(findQry);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            return createCategoryListFromResults(rs);
        } catch (SQLException e) {
            System.out.println("SQLException in getCategoriesForAd");
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

    private List<Category> createCategoryListFromResults(ResultSet rs) throws SQLException {
        List<Category> categories = new ArrayList<>();
        while (rs.next()) {
//            can also be extract category
            categories.add(new Category(
                    rs.getLong("Id"),
                    rs.getString("category")
            ));
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
