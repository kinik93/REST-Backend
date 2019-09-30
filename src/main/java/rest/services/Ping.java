package rest.services;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

@Path("/services/ping")
public class Ping {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String ping(){
        return "Ciao";
    }
}
