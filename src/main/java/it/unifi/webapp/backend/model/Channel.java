package it.unifi.webapp.backend.model;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Channel")
public class Channel extends BaseEntity{


    @OneToOne
    @Expose
    private User owner;

    @Transient
    private List<Video> channelVideos;

    @OneToMany
    private List<User> subscribers;


    public Channel(){}

    public Channel(String uuid, User owner){
        super(uuid);
        this.owner = owner;
    }

    public Channel(String uuid, User owner, List<User> subscribers){
        super(uuid);
        this.owner = owner;
        this.subscribers = subscribers;
    }


    public User getOwner() {
        return owner;
    }

    public List<Video> getChannelVideos() {
        return channelVideos;
    }
    public void setChannelVideos(List<Video> videos){ this.channelVideos=videos;}
    public List<User> getSubscribers() {
        return subscribers;
    }

    public void addSubscriber(User sub){
        this.subscribers.add(sub);
    }
    public void rmSubscriber(User usr){ this.subscribers.remove(usr); }

}
