package it.unifi.webapp.backend.rest.services.services;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import it.unifi.webapp.backend.dao.*;
import it.unifi.webapp.backend.model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

@Path("/services/videos")
public class VideoService {


    @Inject
    private LogSystem logSystem;

    @Inject
    private VideoDao videoDao;

    @Inject
    private ChannelDao channelDao;

    @Inject
    private JsonResponseBuilder jsBuilder;

    @Inject
    private UserDao usrDao;

    @Inject
    private VideoLikeDao videoLikeDao;

    @Inject
    private VideoCommentDao videoCommentDao;



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/watch")
    @Transactional
    public String watchVideo(@QueryParam("videoUUID") String videoUUID, @QueryParam("usrUUID") String usrUUID, @QueryParam("scenario") String scenario, @QueryParam("id") int id){


        String videoId = videoDao.findIdbyUUID(videoUUID, "servicesdb.Video");
        Video video = videoDao.findById(Long.parseLong(videoId));
        jsBuilder.createResponse();
        if (video!=null){
            if(usrUUID!=null){

                String usrID = usrDao.findIdbyUUID(usrUUID, "servicesdb.User");
                boolean isSubscribed = channelDao.findSubscriber(video.getChannel().getId().toString(), usrID);
                jsBuilder.addField("subscribe", isSubscribed);

                boolean isLiked = videoLikeDao.findLike(usrID, videoId);
                jsBuilder.addField("like", isLiked);


            }
            if(scenario!="" && id!=0){

                logSystem.log(scenario, id, "watchVideo");
            }
            return jsBuilder.getJson();
        }
        jsBuilder.addField("status", "Error");


        return jsBuilder.getJson();


    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String searchVideo(@QueryParam("query") String query, @QueryParam("scenario") String scenario, @QueryParam("id") int id){

        List<Video> results = videoDao.getVideosFromKeyWord(query);
        Type listType = new TypeToken<ArrayList<Video>>() {}.getType();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        String json = gson.toJson(results, listType);
        if(scenario!="" && id!=0){
            logSystem.log(scenario, id, "searchVideo");
        }
        return json;

    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String randomVideo(){

        List<Video> results = videoDao.getRandomVideos();
        Type listType = new TypeToken<ArrayList<Video>>() {}.getType();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        String json = gson.toJson(results, listType);
        return json;

    }

    @GET
    @Path("/like")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String likeVideo(@QueryParam("videoUUID") String videoUUID, @QueryParam("usrUUID") String usrUUID, @QueryParam("scenario") String scenario, @QueryParam("id") int id) {

        String videoId = videoDao.findIdbyUUID(videoUUID, "servicesdb.Video");
        Video video = videoDao.findById(Long.parseLong(videoId));
        jsBuilder.createResponse();
        if (video != null) {
            if (usrUUID != null) {

                String usrID = usrDao.findIdbyUUID(usrUUID, "servicesdb.User");
                User usr = usrDao.findById(Long.parseLong(usrID));
                VideoLike vl = new VideoLike(UUID.randomUUID().toString(), usr, video);
                boolean exist = videoLikeDao.findLike(usrID, videoId);

                if(exist) {
                    videoLikeDao.deleteLike(usrID, videoId);
                    jsBuilder.addField("like", false);
                }
                else{
                    videoLikeDao.save(vl);
                    jsBuilder.addField("like", true);
                }
                if(scenario!="" && id!=0){
                    logSystem.log(scenario, id, "like");
                }

            }

            return jsBuilder.getJson();
        }
        jsBuilder.addField("status", "Error");
        return jsBuilder.getJson();


    }


    @GET
    @Path("/comment")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response commentVideo(@QueryParam("videoUUID") String videoUUID, @QueryParam("usrUUID") String usrUUID, @QueryParam("comment") String comment, @QueryParam("scenario") String scenario, @QueryParam("id") int id) {

        String videoId = videoDao.findIdbyUUID(videoUUID, "servicesdb.Video");
        Video video = videoDao.findById(Long.parseLong(videoId));
        if (video != null) {
            if (usrUUID != null) {

                String usrID = usrDao.findIdbyUUID(usrUUID, "servicesdb.User");
                User usr = usrDao.findById(Long.parseLong(usrID));
                VideoComment vc = new VideoComment(UUID.randomUUID().toString(), comment, usr, video);
                videoCommentDao.save(vc);
                if(scenario!="" && id!=0){
                    logSystem.log(scenario, id, "comment");
                }
                return Response.ok().build();
            }
        }
        return Response.serverError().build();


    }



}
