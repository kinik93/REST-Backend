package it.unifi.webapp.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Channel")
public class Channel {


    @OneToOne
    private User owner;

    @OneToMany
    private Video channelVideos;

    @OneToMany
    private User subscribers;

    @OneToMany
    private User observers;



    public User getOwner() {
        return owner;
    }

    /*public List<Video> getChannelVideos() {
        return channelVideos;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }
    public List<User> getObservers() {
        return observers;
    }*/


}
