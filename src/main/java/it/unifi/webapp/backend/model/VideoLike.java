
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


    private String uuid;


    public Timestamp getCommitTime() {
        return commitTime;
    }

    /*public User getWriter() {
        return liker;
    }*/

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
