package it.unifi.webapp.backend.dao;

import it.unifi.webapp.backend.model.Video;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class VideoDao extends BaseDao<Video> {


    public VideoDao() {
        super(Video.class);
    }

    public void save(Video video) {
        if (video.getId() != null) {
            super.update(video);
        } else {
            super.save(video);
        }
    }
}
