package it.unifi.webapp.backend.rest.services.services;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.unifi.webapp.backend.dao.ChannelDao;
import it.unifi.webapp.backend.dao.UserDao;
import it.unifi.webapp.backend.model.Channel;
import it.unifi.webapp.backend.model.User;
import it.unifi.webapp.backend.model.Video;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Path("/services/users")
public class UserService {

    @Inject
    private UserDao usrDao;

    @Inject
    private ChannelDao chDao;

    private class ResponseStatus{
        boolean status;
        String username;
        String uuid;
        private ResponseStatus(String username, boolean status, String uuid){
            this.status = status;
            this.username = username;
            this.uuid = uuid;
        }
    }

    @Path("/login")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String signIN(@QueryParam("username") String username, @QueryParam("psw") String psw) {

        Gson gsonBuilder = new GsonBuilder().create();
        User usr = usrDao.findByUsername(username, psw);
        String jsonResp = gsonBuilder.toJson(new ResponseStatus(username, false, ""));
        if( usr != null){
            jsonResp = gsonBuilder.toJson(new ResponseStatus(username, true, usr.getUuid()));
        }

        return jsonResp;
    }

    @Path("/followedChannels")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String getFollowedChannels(@QueryParam("usrUUID") String usrUUID) {

        String usrId = usrDao.findIdbyUUID(usrUUID, "servicesdb.User");
        List<BigInteger> channelIds = chDao.getFollowedChannels(usrId);
        List<Channel> channels = new ArrayList<>();
        for (BigInteger id : channelIds){
            channels.add(chDao.findById(Long.parseLong(id.toString())));
        }
        Type listType = new TypeToken<ArrayList<Channel>>() {}.getType();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        String json = gson.toJson(channels, listType);
        return json;

    }


}
