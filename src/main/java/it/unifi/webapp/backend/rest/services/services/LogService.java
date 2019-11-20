package it.unifi.webapp.backend.rest.services.services;


import it.unifi.webapp.backend.model.LogSystem;
import it.unifi.webapp.backend.model.SessionScenario;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Random;

@Path("services/log")
public class LogService {



    @Inject
    JsonResponseBuilder jsBuilder;

    @Inject
    LogSystem ls;


    public void getData(@QueryParam("data") String data, @QueryParam("scenario") String scenario) {


        try{

            String yourSystemPath = System.getProperty("jboss.server.data.dir");
            FileWriter fw = new FileWriter(yourSystemPath+"/"+scenario+".csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);

            out.println(data);
            System.out.println(data);
            out.close();

        } catch (IOException e) {


        }


    }

    @GET
    @Path("/ID")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String getID() {

        jsBuilder.createResponse();
        int id = new Random().nextInt(1000);
        jsBuilder.addField("ID", id);
        return jsBuilder.getJson();

    }

    @GET
    @Path("/startScenario")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public Response startScenarioSession(@QueryParam("scenario") String scenario, @QueryParam("id") int id) {

        if(scenario!="" && id!=0){
            ls.addSessionScenario(new SessionScenario(scenario, id));
            System.out.println("Started scenario: "+scenario+ " for session id: "+id);
            return Response.ok().build();
        }
        return Response.status(400).build();

    }

    @GET
    @Path("/endScenario")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public Response endScenarioSession(@QueryParam("scenario") String scenario, @QueryParam("id") int id) {


        if(scenario!=null && id!=0){
            System.out.println("Finished scenario: "+scenario+ " for session id: "+id);
            ls.endScenario(scenario, id);
            return Response.ok().build();
        }
        return Response.serverError().build();

    }


}
