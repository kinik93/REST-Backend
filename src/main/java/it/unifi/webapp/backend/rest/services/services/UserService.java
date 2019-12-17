package it.unifi.webapp.backend.rest.services.services;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.unifi.webapp.backend.dao.ChannelDao;
import it.unifi.webapp.backend.dao.SubscriberDao;
import it.unifi.webapp.backend.dao.UserDao;
import it.unifi.webapp.backend.model.Channel;
import it.unifi.webapp.backend.model.LogSystem;
import it.unifi.webapp.backend.model.User;
import it.unifi.webapp.backend.model.Video;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Inject
    private SubscriberDao subDao;


    @Path("/signup")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public Response signUP(@QueryParam("username") String username, @QueryParam("psw") String psw) {

        if(username != null && psw != null) {
            User user = new User(UUID.randomUUID().toString(), username, psw);
            Channel ch = new Channel(UUID.randomUUID().toString(), user);
            subDao.save(user);
            chDao.save(ch);
            return Response.ok().build();
        }
        return Response.serverError().build();
    }


    @Path("/login")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String signIN(@QueryParam("username") String username, @QueryParam("psw") String psw, @QueryParam("scenario") String scenario, @QueryParam("id") int id) {

        Gson gsonBuilder = new GsonBuilder().create();
        User usr = usrDao.findByUsername(username, psw);
        jsBuilder.createResponse();
        if( usr != null){
            jsBuilder.addField("username", username);
            jsBuilder.addField("uuid", usr.getUuid());
            String chUUID = chDao.getFromUserid(usr.getId().toString()).getUuid();
            jsBuilder.addField("chUUID", chUUID);
            jsBuilder.addField("status", true);
            if(scenario!="" && id!=0){
                logSystem.log(scenario, id, "login", 4);
            }
        }
        else{
            jsBuilder.addField("status", false);
        }

        return jsBuilder.getJson();

    }

    @Path("/logout")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response logout(@QueryParam("scenario") String scenario, @QueryParam("id") int id) {

        if(id!=0){
            logSystem.log(scenario, id, "logout", 9);
            return Response.ok().build();
        }

        return Response.status(400).build();

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
        return json;

    }


}
