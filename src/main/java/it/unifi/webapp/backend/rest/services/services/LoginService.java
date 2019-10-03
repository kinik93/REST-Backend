package it.unifi.webapp.backend.rest.services.services;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unifi.webapp.backend.dao.LoginDao;
import it.unifi.webapp.backend.model.User;
import javax.transaction.Transactional;
import java.util.UUID;

@Path("/services/login")
public class LoginService {

    @Inject
    private LoginDao logDao;

    private class ResponseStatus{
        boolean status;
        private ResponseStatus(boolean status){
            this.status = status;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String signIN(@QueryParam("username") String username, @QueryParam("psw") String psw) {
        User user = new User(UUID.randomUUID().toString(), username, psw);


        Gson gsonBuilder = new GsonBuilder().create();
        String jsonResp = gsonBuilder.toJson(new ResponseStatus(logDao.login(user)));
        return jsonResp;
    }
}
