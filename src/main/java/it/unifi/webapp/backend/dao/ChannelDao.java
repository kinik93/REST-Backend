package it.unifi.webapp.backend.dao;

import it.unifi.webapp.backend.model.Channel;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
public class ChannelDao extends BaseDao<Channel> {


    public ChannelDao() {
        super(Channel.class);
    }

    public void save(Channel ch) {
        if (ch.getId() != null) {
            super.update(ch);
        } else {
            super.save(ch);
        }
    }


    public Channel get(String chUUID){

            Channel result = entityManager
                    .createQuery("from Channel "
                            + "where uuid = :uid", Channel.class)
                    .setParameter("uid", chUUID)
                    .getSingleResult();

            return result;

    }

    public Channel getFromUserid(String UserId){
        return entityManager
                .createQuery("from Channel "
                        + "where owner_id = :usrId", Channel.class)
                        .setParameter("usrId", UserId)
                        .getSingleResult();

    }

    public boolean findSubscriber( String chID, String usrID) {
        Query q = entityManager.createNativeQuery("SELECT * FROM servicesdb.Channel_User WHERE Channel_id=:chID AND subscribers_id=:usrID")
                .setParameter("chID", chID)
                .setParameter("usrID", usrID);
        try{
            Object result = q.getSingleResult();
            return true;
        }
        catch (NoResultException nre){
            return false;
        }

    }

    public List<BigInteger> getFollowedChannels( String usrID) {
        Query q = entityManager.createNativeQuery("SELECT Channel_id FROM servicesdb.Channel_User WHERE subscribers_id=:usrID")
                .setParameter("usrID", usrID);
        try{
            List<BigInteger> result = q.getResultList();
            return result;
        }
        catch (NoResultException nre){
            return null;
        }

    }

}
