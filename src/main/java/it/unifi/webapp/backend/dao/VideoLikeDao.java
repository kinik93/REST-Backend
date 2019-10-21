package it.unifi.webapp.backend.dao;

import it.unifi.webapp.backend.model.VideoLike;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class VideoLikeDao extends BaseDao<VideoLike> {


    public VideoLikeDao() {
        super(VideoLike.class);
    }

    public void save(VideoLike vl) {
        if (vl.getId() != null) {
            super.update(vl);
        } else {
            super.save(vl);
        }
    }

    public boolean findLike( String likerID, String videoID) {
        Query q = entityManager.createNativeQuery("SELECT * FROM servicesdb.VideoLike WHERE liker_id=:likerID AND video_id=:videoID")
                .setParameter("likerID", likerID)
                .setParameter("videoID", videoID);
        try{
            Object result = q.getSingleResult();
            return true;
        }
        catch (NoResultException nre){
            return false;
        }

    }

    public boolean deleteLike( String likerID, String videoID) {
        Query q = entityManager.createNativeQuery("DELETE FROM servicesdb.VideoLike WHERE liker_id=:likerID AND video_id=:videoID")
                .setParameter("likerID", likerID)
                .setParameter("videoID", videoID);
        try{
            Object result = q.getSingleResult();
            return true;
        }
        catch (NoResultException nre){
            return false;
        }

    }


}
