package it.unifi.webapp.backend.rest.services.services;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import it.unifi.webapp.backend.dao.UserDao;
import it.unifi.webapp.backend.model.User;
import it.unifi.webapp.backend.model.Video;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/services/videos")
public class VideoService {

    @Inject
    private UserDao userDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String ping(){
        Video video = new Video("Shallow", "Tell me something girl...");
        Gson gsonBuilder = new GsonBuilder().create();
        String jsonResp = gsonBuilder.toJson(video);
        return jsonResp;
    }

    @GET
    @Path("/prova")
    @Produces(MediaType.TEXT_PLAIN)
    public String prova(){
        /*EntityManagerFactory factory = Persistence.createEntityManagerFactory("myServiceUnit");
        EntityManager entityManager = factory.createEntityManager();


        entityManager.getTransaction().begin();

        try {
            entityManager.createNativeQuery("DROP table A;").executeUpdate();
        }
        catch(Exception e){
            return ""+e;
        }
        entityManager.getTransaction().commit();

        entityManager.close();
        factory.close();*/

        User tmp = new User();
        tmp.setUsername("ciao");
        tmp.setUuid("mondo");
        userDao.save(tmp);

        return "ciao";
    }
}
