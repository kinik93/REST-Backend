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
import it.unifi.webapp.backend.model.LogSystem;
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

    @Inject
    private JsonResponseBuilder jsBuilder;

    @Inject
    private LogSystem logSystem;

    @Path("/login")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String signIN(@QueryParam("username") String username, @QueryParam("psw") String psw, @QueryParam("scenario") String scenario, @QueryParam("id") int id) {

        Gson gsonBuilder = new GsonBuilder().create();
        User usr = usrDao.findByUsername(username, psw);
        jsBuilder.createResponse();
        jsBuilder.addField("username", username);
        jsBuilder.addField("uuid", usr.getUuid());
        if( usr != null){
            String chUUID = chDao.getFromUserid(usr.getId().toString()).getUuid();
            jsBuilder.addField("chUUID", chUUID);
            jsBuilder.addField("status", true);
            if(scenario!="" && id!=0){
                logSystem.log(scenario, id, "login");
            }
        }
        else{
            jsBuilder.addField("status", false);
        }

        return jsBuilder.getJson();

    }

    @Path("/followedChannels")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String getFollowedChannels(@QueryParam("usrUUID") String usrUUID, @QueryParam("scenario") String scenario, @QueryParam("id") int id) {

        String usrId = usrDao.findIdbyUUID(usrUUID, "servicesdb.User");
        List<BigInteger> channelIds = chDao.getFollowedChannels(usrId);
        List<Channel> channels = new ArrayList<>();
        for (BigInteger ID : channelIds){
            channels.add(chDao.findById(Long.parseLong(ID.toString())));
        }
        Type listType = new TypeToken<ArrayList<Channel>>() {}.getType();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        String json = gson.toJson(channels, listType);
        if(scenario!="" && id!=0){
            logSystem.log(scenario, id, "followedChannels");
        }
        return json;

    }


}
