package it.unifi.webapp.backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "SubscribedUser")
public class SubscribedUser extends User{

    private String nickname;
    private String email;
    private String password;
    private Channel profile;
    private List<Channel> followedChannel;

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Channel getProfile() {
        return profile;
    }

    public List<Channel> getFollowedChannel() {
        return followedChannel;
    }

}
