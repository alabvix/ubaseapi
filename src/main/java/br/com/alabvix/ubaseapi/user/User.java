package br.com.alabvix.ubaseapi.user;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class User {

    @Id
    public final String id;
    public final String name;
    public final String email;
    public final String password;
    public final Date creationDate;

    public User(String id, String name, String email, String password, Date creationDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.creationDate = creationDate;
    }
}
