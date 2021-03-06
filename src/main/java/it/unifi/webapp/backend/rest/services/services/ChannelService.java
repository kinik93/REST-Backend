package it.unifi.webapp.backend.rest.services.services;
import javax.inject.Inject;
import javax.validation.constraints.Null;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import it.unifi.webapp.backend.dao.UserDao;
import it.unifi.webapp.backend.model.LogSystem;
import it.unifi.webapp.backend.model.Video;
import it.unifi.webapp.backend.model.Channel;
import it.unifi.webapp.backend.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unifi.webapp.backend.dao.VideoDao;
import it.unifi.webapp.backend.dao.ChannelDao;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("/services/channel")
public class ChannelService {




    @Inject
    private LogSystem logSystem;

    @Inject
    private VideoDao videoDao;

    @Inject
    private ChannelDao channelDao;

    @Inject
    private UserDao usrDao;

    @Inject
    private VideoService videoService;

    @Inject
    private JsonResponseBuilder jsBuilder;

    @GET
    @Path("/uploadvideo")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String uploadVideo(@QueryParam("videoname") String videoname, @QueryParam("descriptor") String descriptor, @QueryParam("channelUUID") String channelUUID, @QueryParam("scenario") String scenario, @QueryParam("id") int id){

        jsBuilder.createResponse();
        Channel ch = channelDao.get(channelUUID);
        if (ch != null){
            Video video = new Video(UUID.randomUUID().toString(), videoname, descriptor, ch);
            videoDao.save(video);
            jsBuilder.addField("status", true);
        }
        else{
            jsBuilder.addField("status", false);
        }
        if(scenario!="" && id!=0){
            logSystem.log(scenario, id, "uploadVideo", 8);
        }

        return jsBuilder.getJson();

    }

    @GET
    @Path("/deletevideo")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteVideo(@QueryParam("videoUUID") String videoUUID, @QueryParam("usrUUID") String usrUUID){

        String usrId = usrDao.findIdbyUUID(usrUUID, "servicesdb.User");
        Channel ch = channelDao.getFromUserid(usrId);
        if (ch != null){
            Video vid = videoDao.findById(Long.parseLong(videoDao.findIdbyUUID(videoUUID, "servicesdb.Video")));
            if(vid != null){
                if(vid.getChannel().getId() == ch.getId()){
                    videoDao.remove(vid);
                    return Response.ok().build();
                }
            }

        }
        return Response.status(400).build();

    }

    @GET
    @Path("/checkSubscription")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String checkSubscription(@QueryParam("userUUID") String usrUUID, @QueryParam("channelUUID") String channelUUID){

        jsBuilder.createResponse();
        Channel ch = channelDao.get(channelUUID);

        if(usrUUID != ""){
            String usrID = usrDao.findIdbyUUID(usrUUID, "servicesdb.User");
            boolean isSubscribed = channelDao.findSubscriber(ch.getId().toString(), usrID);
            jsBuilder.addField("subscribe", isSubscribed);
        }

        return jsBuilder.getJson();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String getChannelFromUserUUID(@QueryParam("userUUID") String userUUID, @QueryParam("scenario") String scenario, @QueryParam("id") int id){

        String usrId = usrDao.findIdbyUUID(userUUID, "servicesdb.User");
        Channel ch = channelDao.getFromUserid(usrId);
        jsBuilder.createResponse();
        if (ch != null){
            jsBuilder.addField("chUUID", ch.getUuid());

        }
        else{
            jsBuilder.addField("status", false);
        }
        return jsBuilder.getJson();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/viewchannel")
    @Transactional
    public String viewChannel(@QueryParam("chUUID") String chUUID, @QueryParam("scenario") String scenario, @QueryParam("id") int id){

        if(chUUID!="") {
            String chId = channelDao.findIdbyUUID(chUUID, "servicesdb.Channel");
            Channel ch = channelDao.findById(Long.parseLong(chId));
            List<Video> videos = videoDao.getVideosFromChannelId(Long.parseLong(chId));
            ch.setChannelVideos(videos);
            jsBuilder.createResponse();
            if (ch != null) {
                Type listType = new TypeToken<ArrayList<Video>>() {
                }.getType();
                Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

                String json = gson.toJson(videos, listType);
                if (scenario != "" && id != 0) {
                    logSystem.log(scenario, id, "viewChannel", 3);
                }
                return json;
            }
        }

        else{
            jsBuilder.addField("status", false);
        }
        return jsBuilder.getJson();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getownername")
    @Transactional
    public String getOwnerName(@QueryParam("chUUID") String chUUID){

        String chId = channelDao.findIdbyUUID(chUUID, "servicesdb.Channel");
        Channel ch = channelDao.findById(Long.parseLong(chId));
        jsBuilder.createResponse();
        if (ch != null){
            jsBuilder.addField("owner", ch.getOwner().getUsername());
        }
        else{
            jsBuilder.addField("owner", null);
        }
        return jsBuilder.getJson();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/subscribe")
    @Transactional
    public Response subChannel(@QueryParam("usrUUID") String usrUUID, @QueryParam("chUUID") String chUUID, @QueryParam("scenario") String scenario, @QueryParam("id") int id){

        String chId = channelDao.findIdbyUUID(chUUID, "servicesdb.Channel");
        String usrId = usrDao.findIdbyUUID(usrUUID, "servicesdb.User");
        User usr = usrDao.findById(Long.parseLong(usrId));
        Channel ch = channelDao.findById(Long.parseLong(chId));
        if(usr!= null && ch!=null) {

            if(ch.getSubscribers().contains(usr)){
                ch.rmSubscriber(usr);
            }
            else{
                ch.addSubscriber(usr);
            }
            if(scenario!="" && id!=0){
                logSystem.log(scenario, id, "subscribe", 7);
            }
            return Response.ok().build();
        }
        else{
            return Response.serverError().build();
        }

    }

}

