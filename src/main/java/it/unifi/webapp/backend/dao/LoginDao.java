package it.unifi.webapp.backend.dao;
import it.unifi.webapp.backend.model.User;

import java.util.List;
public class LoginDao extends BaseDao<User>{

    public LoginDao() {
        super(User.class);
    }

    public boolean login(User user) {

        if (user.getUsername() != null) {
            return findByUsername(user.getUsername(), user.getPsw());
        }
        return false;
    }

    private boolean findByUsername( String username, String psw) {
        List <User> results =  entityManager
                .createQuery("from User "
                        + "where username = :username and psw = :psw", User.class)
                .setParameter("username", username)
                .setParameter("psw", psw)
                .getResultList();
        if (results.size() > 0)
            return true;
        return false;

    }
}
