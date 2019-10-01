package it.unifi.webapp.backend.dao;

import it.unifi.webapp.backend.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserDao {

    @PersistenceContext(unitName = "myServiceUnit")
    protected EntityManager em;

    public void save(User user){
        try{
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }
        catch (Exception e){

        }
    }
}
