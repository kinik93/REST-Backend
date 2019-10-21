package it.unifi.webapp.backend.dao;

import it.unifi.webapp.backend.model.Video;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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


    public List<Video> getVideosFromKeyWord(String key){

        List <Video> result = entityManager
                .createQuery("from Video "
                        + "where name like '%"+key+"%'", Video.class)
                .getResultList();

        return result;


    }


    public List<Video> getRandomVideos(){

        List <Video> result = entityManager
                .createQuery("from Video", Video.class)
                .getResultList();

        return result;

    }

    public List<Video> getVideosFromChannelId(Long id){

        List <Video> results = entityManager
                .createQuery("from Video where channel_id=:id", Video.class)
                .setParameter("id", id)
                .getResultList();

        return results;

    }
}
