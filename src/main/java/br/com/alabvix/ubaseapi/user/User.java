package br.com.alabvix.ubaseapi.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class User implements Serializable, UserDetails {

    @Id
    public final String id;

    public final String username;

    public final String email;

    public final String password;

    public final Date creationDate;

    /**
     * This constructor is for jackson serialization on authentication process
     */
    public User() {
        this.id = "";
        this.username = "";
        this.email = "";
        this.password = "";
        this.creationDate = null;
    }

    /**
     * This constructor will be used by Spring:MappingMongoConverter to
     * create the object for the mongoDb.
     */
    @PersistenceConstructor
    public User(String id, String username, String email, String password, Date creationDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.creationDate = creationDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
