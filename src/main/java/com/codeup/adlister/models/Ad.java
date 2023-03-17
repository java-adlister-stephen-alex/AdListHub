package com.codeup.adlister.models;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Ad {
    private long id;
    private long userId;
    private String title;
    private String description;
    private int price;

    public Ad(long userId, String title, String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    public Ad(long userId, String title, String description, int price) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.price = price;
    }
}
