package com.codeup.adlister.models;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ad {
    private long id;
    private long userId;
    private String title;
    private String description;
    private Long price;
    private List<Category> adCategories;

    public Ad(long userId, String title, String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    public Ad(long userId, String title, String description, Long price) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public Ad(long userId, String title, String description, Long price, List<Category> adCategories) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.adCategories = adCategories;
    }
}
