package it.unifi.webapp.backend.dao;

import it.unifi.webapp.backend.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class UserDao extends BaseDao<User>{


    public UserDao() {
        super(User.class);
    }
    public void save(User user){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){

        }
    }

    public boolean login(User user) {

        if (user.getUsername() != null) {
            return (findByUsername(user.getUsername(), user.getPsw())!=null);
        }
        return false;
    }


    public User findByUsername( String username, String psw) {

        try {


            User result = entityManager
                    .createQuery("from User "
                            + "where username = :username and psw = :psw", User.class)
                    .setParameter("username", username)
                    .setParameter("psw", psw)
                    .getSingleResult();
            return result;
        }
        catch (Exception e){
            return null;
        }

    }

}
