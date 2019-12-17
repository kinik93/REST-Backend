package it.unifi.webapp.backend.dao;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import com.mysql.cj.Session;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public abstract class BaseDao<E> implements Serializable {

    private static final long serialVersionUID = 481224455868183449L;

    private final Class<E> type;

    @PersistenceContext(unitName = "myServiceUnit")
    protected EntityManager entityManager;

    public BaseDao( Class<E> type ) {
        this.type = type;
    }

    public E findById( Long id ) {
        return entityManager.find( type, id );
    }

    public String findIdbyUUID( String uuid, String tableName) {
        Query q = entityManager.createNativeQuery("SELECT id FROM "+tableName+" WHERE uuid=:UUID").setParameter("UUID", uuid);

        try{
            Object result = q.getSingleResult();
            return result.toString();
        }
        catch(NoResultException nre){
            return "";
        }

    }

    public void update( E entity ) {
        entityManager.merge( entity );
    }
    public void remove( E entity ) {
        entityManager.remove( entity );
    }

    public void save( E entity ) {
        entityManager.persist( entity );
    }

}
