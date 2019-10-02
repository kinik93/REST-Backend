package it.unifi.webapp.backend.dao;

import it.unifi.webapp.backend.model.VideoComment;

import it.unifi.webapp.backend.model.Video;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class VideoCommentDao extends BaseDao<VideoComment> {


    public VideoCommentDao() {
        super(VideoComment.class);
    }

    public void save(VideoComment comment) {
        if (comment.getId() != null) {
            super.update(comment);
        } else {
            super.save(comment);
        }
    }
}

