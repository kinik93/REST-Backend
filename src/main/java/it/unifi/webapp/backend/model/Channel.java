package it.unifi.webapp.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Channel")
public class Channel extends BaseEntity{


    @OneToOne
    private User owner;

    @OneToMany
    private List<Video> channelVideos;

    @OneToMany
    private List<User> subscribers;

    @OneToMany
    private List<User> observers;



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
