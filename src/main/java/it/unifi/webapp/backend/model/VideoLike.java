
package it.unifi.webapp.backend.model;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="VideoLike")
public class VideoLike extends BaseEntity{


    private Timestamp commitTime;

    @ManyToOne
    private User liker;

    @ManyToOne
    private Video video;

    public VideoLike(){}

    public VideoLike(String uuid, User liker, Video video){
        super(uuid);
        this.liker = liker;
        this.video = video;
        this.commitTime = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getCommitTime() {
        return commitTime;
    }

    /*public User getWriter() {
        return liker;
    }*/

    public Video getVideo() {
        return video;
    }

}
