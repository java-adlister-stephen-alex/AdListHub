package com.codeup.adlister.models;

import lombok.*;
import org.mindrot.jbcrypt.BCrypt;
import util.Password;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private long id;
    private String username;
    private String email;
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = Password.hash(password);
    }
}

