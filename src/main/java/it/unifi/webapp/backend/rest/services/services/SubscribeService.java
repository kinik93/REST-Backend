package it.unifi.webapp.backend.rest.services.services;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import it.unifi.webapp.backend.dao.SubscriberDao;
import it.unifi.webapp.backend.dao.ChannelDao;
import it.unifi.webapp.backend.model.User;
import it.unifi.webapp.backend.model.Channel;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.UUID;


@Path("/services/signup")
public class SubscribeService {

    @Inject
    private SubscriberDao subDao;

    @Inject
    private ChannelDao chDao;



    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String signUP(@QueryParam("username") String username, @QueryParam("psw") String psw) {

        if(username != null && psw != null) {
            User user = new User(UUID.randomUUID().toString(), username, psw);
            Channel ch = new Channel(UUID.randomUUID().toString(), user);
            subDao.save(user);
            chDao.save(ch);
            return "ok";
        }
        return "error";
    }
}
