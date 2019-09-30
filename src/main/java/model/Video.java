package model;
import java.util.List;

public class Video {


    private String name;
    private List<VideoComment> comments;
    private List<VideoLike> likes;

    //This is something like a payload for our purposes
    private String videoDescriptor;


    public Video(String name, String videoDescriptor) {
        this.name = name;
        this.videoDescriptor = videoDescriptor;
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
