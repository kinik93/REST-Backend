package it.unifi.webapp.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {


    private String username;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
