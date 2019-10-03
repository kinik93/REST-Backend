package it.unifi.webapp.backend.rest.services.services;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import it.unifi.webapp.backend.dao.LoginDao;
import it.unifi.webapp.backend.model.User;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.UUID;

@Path("/services/login")
public class LoginService {

    @Inject
    private LoginDao logDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String signIN(@QueryParam("username") String username, @QueryParam("psw") String psw) {
        User user = new User(UUID.randomUUID().toString(), username, psw);
        if(logDao.login(user))
            return "logged";
        return "Not logged";
    }
}
