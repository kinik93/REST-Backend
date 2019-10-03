package it.unifi.webapp.backend.rest.services.services;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import it.unifi.webapp.backend.dao.SubscriberDao;
import it.unifi.webapp.backend.model.User;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.UUID;


@Path("/services/subscribe")
public class SubscribeService {

    @Inject
    private SubscriberDao subDao;


    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping(){
        return "Pinga";
    }

    @GET
    @Path("/signup/")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String signUP(@QueryParam("username") String username, @QueryParam("psw") String psw) {
        User user = new User(UUID.randomUUID().toString(), username, psw);
        subDao.save(user);
        return "ok";
    }
}
