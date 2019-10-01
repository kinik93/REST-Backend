package it.unifi.webapp.backend.model;
import java.sql.Timestamp;

public class VideoComment {

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
}
