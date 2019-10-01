
package it.unifi.webapp.backend.model;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="VideoLike")
public class VideoLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int likeId;


    private Timestamp commitTime;
    private User liker;
    private Video video;
    private String uuid;


    public Timestamp getCommitTime() {
        return commitTime;
    }

    public User getWriter() {
        return liker;
    }

    public Video getVideo() {
        return video;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
