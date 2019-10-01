package model;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int videoId;

    private String uuid;

    private String name;
    private List<VideoComment> comments;
    private List<VideoLike> likes;

    //This is something like a payload for our purposes
    private String videoDescriptor;


    public Video(String name, String videoDescriptor) {
        this.name = name;
        this.videoDescriptor = videoDescriptor;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public List<VideoComment> getComments() {
        return comments;
    }

    public List<VideoLike> getLikes() {
        return likes;
    }

    public String getVideoDescriptor() {
        return videoDescriptor;
    }


}
