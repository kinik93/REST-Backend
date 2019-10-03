package it.unifi.webapp.backend.rest.services.services;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import it.unifi.webapp.backend.dao.VideoCommentDao;
import it.unifi.webapp.backend.model.VideoComment;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.UUID;

@Path("/services/comments")
public class CommentService {

    @Inject
    private VideoCommentDao commentDao;

    @GET
    @Path("/postcomment/{text}")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public Response commentVideo(@PathParam("text") String text) {
        VideoComment comment = new VideoComment(UUID.randomUUID().toString(), text, new Timestamp(System.currentTimeMillis()));
        commentDao.save(comment);

        return Response
                .ok()
                .entity("Ok")
                .build();
    }
}
