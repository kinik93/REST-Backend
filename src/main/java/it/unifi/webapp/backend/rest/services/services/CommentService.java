package it.unifi.webapp.backend.rest.services.services;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import it.unifi.webapp.backend.dao.UserDao;
import it.unifi.webapp.backend.model.User;
import it.unifi.webapp.backend.model.Video;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unifi.webapp.backend.dao.VideoDao;
import java.util.*;
import javax.transaction.Transactional;

import java.util.UUID;

@Path("/services/comments")
public class CommentService {

    @GET
    @Path("/postcomment/{}")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String prova() {
        Video video = new Video(UUID.randomUUID().toString(), "Shallow", "Tell me something girl...");
        videoDao.save(video);
    }
}
