package it.unifi.webapp.backend.model;
import java.sql.Timestamp;

public class VideoLike {

    private Timestamp commitTime;
    private User liker;
    private Video video;
    private String likeId;

    public Timestamp getCommitTime() {
        return commitTime;
    }

    public User getWriter() {
        return liker;
    }

    public Video getVideo() {
        return video;
    }

    public String getLikeId(){
        return likeId;
    }
}
