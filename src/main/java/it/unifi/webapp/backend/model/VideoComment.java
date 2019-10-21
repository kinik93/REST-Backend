
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

    public VideoComment(){}
    public VideoComment(String uuid, String text, User writer, Video video){
        super(uuid);
        commentText = text;
        this.video = video;
        this.writer = writer;
        this.commitTime = new Timestamp(System.currentTimeMillis());

    }
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
