package it.unifi.webapp.backend.dao;

import it.unifi.webapp.backend.model.User;

public class SubscriberDao extends BaseDao<User>{

    public SubscriberDao() {
        super(User.class);
    }

    public void save(User user) {
        if (user.getId() != null) {
            super.update(user);
        } else {
            super.save(user);
        }
    }
}
