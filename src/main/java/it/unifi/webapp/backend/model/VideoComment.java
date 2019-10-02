
package it.unifi.webapp.backend.model;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "VideoComment")
public class VideoComment extends BaseEntity{


    private Timestamp commitTime;

    @ManyToOne
    private User writer;

    @ManyToOne
    private Video video;

    private String commentText;

    public Timestamp getCommitTime() {
        return commitTime;
    }

    /*public User getWriter() {
        return writer;
    }

    public Video getVideo() {
        return video;
    }
    */
    public String getCommentText() {
        return commentText;
    }


}
