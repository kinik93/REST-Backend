package it.unifi.webapp.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User extends BaseEntity{


    private String username;
    private String psw;

    public User(){}
    public User(String uuid, String name, String psw) {
        super(uuid);
        this.username = name;
        this.psw = psw;
    }

    public String getUsername() {
        return username;
    }
    public String getPsw() {
        return psw;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
