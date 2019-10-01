package it.unifi.webapp.backend.model;

import java.util.List;


public class Channel {


    private User owner;
    private List<Video> channelVideos;
    private List<User> subscribers;
    private List<User> observers;



    public User getOwner() {
        return owner;
    }

    public List<Video> getChannelVideos() {
        return channelVideos;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }
    public List<User> getObservers() {
        return observers;
    }
}
