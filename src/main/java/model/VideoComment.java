package model;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "VideoComment")
public class VideoComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String uuid;

    private Timestamp commitTime;
    private User writer;
    private Video video;
    private String commentText;

    public Timestamp getCommitTime() {
        return commitTime;
    }

    public User getWriter() {
        return writer;
    }

    public Video getVideo() {
        return video;
    }

    public String getCommentText() {
        return commentText;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
