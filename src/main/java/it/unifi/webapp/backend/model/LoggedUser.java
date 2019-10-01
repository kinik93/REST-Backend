package it.unifi.webapp.backend.model;

import java.util.List;

public class LoggedUser extends User{

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
