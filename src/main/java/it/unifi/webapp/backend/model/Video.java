package it.unifi.webapp.backend.model;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Video")
public class Video extends BaseEntity{


    private String name;

    @OneToMany
    private List<VideoComment> comments;

    @OneToMany
    private List<VideoLike> likes;


//    private List<VideoLike> likes;
//
//    //This is something like a payload for our purposes
    private String videoDescriptor;
//
//
    public Video(String name, String videoDescriptor) {
        this.name = name;
        this.videoDescriptor = videoDescriptor;
    }


    public String getName() {
        return name;
    }

    /*public List<VideoComment> getComments() {
        return comments;
    }

    public List<VideoLike> getLikes() {
        return likes;
    }*/

    public String getVideoDescriptor() {
        return videoDescriptor;
    }


}
