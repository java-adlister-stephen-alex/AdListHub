package com.codeup.adlister.models;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private long id;
    private String category;

    @Override
    public String toString() {
        return category;
    }
}
