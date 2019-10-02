package it.unifi.webapp.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Channel")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String uuid;
//    private User owner;
//    private List<Video> channelVideos;
//    private List<User> subscribers;
//    private List<User> observers;
//
//
//
//    public User getOwner() {
//        return owner;
//    }
//
//    public List<Video> getChannelVideos() {
//        return channelVideos;
//    }
//
//    public List<User> getSubscribers() {
//        return subscribers;
//    }
//    public List<User> getObservers() {
//        return observers;
//    }
//
//    public String getUuid() {
//        return uuid;
//    }
//
//    public void setUuid(String uuid) {
//        this.uuid = uuid;
//    }
}
