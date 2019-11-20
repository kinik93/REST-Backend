package it.unifi.webapp.backend.model;

import java.util.ArrayList;
import java.util.List;
public class LogSystem {

    private static List<SessionScenario> ssList = new ArrayList<>();


    public SessionScenario getSessionScenario(String name, int id){

        for (SessionScenario ss : ssList){

            if (ss.getScenario().equals(name) && ss.getId()==id){

                return ss;
            }


        }
        return null;
    }



    public void addSessionScenario(SessionScenario ss){

       ssList.add(ss);
       System.out.println("Actual number of active scenarios: "+ssList.size());
    }

    public void endScenario(String scenario, int id){

        SessionScenario ss = this.getSessionScenario(scenario, id);
        if (ss != null) {
            ss.writeFile();
            ssList.remove(ss);
        }
    }

    public void log(String scenario, int id, String eventName){
        SessionScenario ss = this.getSessionScenario(scenario, id);

        if (ss != null) {
            long timeS = ss.getStartTime();
            EndpointEvent ee = new EndpointEvent(eventName, System.currentTimeMillis() - timeS);
            System.out.println(ee.toString());
            this.getSessionScenario(scenario, id).addEndpointEvent(ee);
        }
        else{
            System.out.println("No valid scenario started for id: "+id+" this call will not be logged");
        }
    }
}
