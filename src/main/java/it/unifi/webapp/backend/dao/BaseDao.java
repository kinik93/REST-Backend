package it.unifi.webapp.backend.dao;
import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDao<E> implements Serializable {

    private static final long serialVersionUID = 481224455868183449L;

    private final Class<E> type;

    @PersistenceContext
    protected EntityManager entityManager;

    public BaseDao( Class<E> type ) {
        this.type = type;
    }

    public E findById( Long id ) {
        return entityManager.find( type, id );
    }


    public void update( E entity ) {
        entityManager.merge( entity );
    }

    public void save( E entity ) {
        entityManager.persist( entity );
    }

}
