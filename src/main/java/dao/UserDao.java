package dao;

import model.User;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserDao {

    @PersistenceContext(unitName = "myServiceUnit")
    protected EntityManager em;

    public void save(User u){
        try{
            em.persist(u);
        }
        catch (Exception e){

        }
    }
}
