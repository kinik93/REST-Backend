package rest.services;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import model.Video;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/services/videos")
public class VideoService {

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
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myServiceUnit");
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
        factory.close();

        return "ciao";
    }
}
