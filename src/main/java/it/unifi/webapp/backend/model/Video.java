package it.unifi.webapp.backend.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Video")
public class Video extends BaseEntity{

    @Expose
    private String name;


    @Transient
    private List<VideoComment> comments;

    @Expose
    @Transient
    private List<VideoLike> likes;

    @Expose
    @ManyToOne
    private Channel channel;



    //This is something like a payload for our purposes
    @Expose
    private String videoDescriptor;


    public Video(){}

    public Video(String uuid, String name, String videoDescriptor, Channel ch) {
        super(uuid);
        this.name = name;
        this.videoDescriptor = videoDescriptor;
        this.channel = ch;

    }



    public String getName() {
        return name;
    }


    public Channel getChannel(){
        return this.channel;
    }
    public String getVideoDescriptor() {
        return videoDescriptor;
    }




}
