package it.unifi.webapp.backend.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.System;
import java.util.ArrayList;
import java.util.List;

public class SessionScenario {


    private List<EndpointEvent> eeList;
    private long startTime;
    private String scenario;
    private int id;

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public int getId() {
        return id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setId(int id) {

        this.id = id;
    }

    public SessionScenario(String scenario, int id){
        eeList = new ArrayList<>();
        startTime = System.currentTimeMillis();
        this.scenario = scenario;
        this.id = id;
    }

    public EndpointEvent getEndpointEvent(int index){

        if (index<eeList.size())
            return eeList.get(index);
        return null;
    }


    public void addEndpointEvent(EndpointEvent ee){

        eeList.add(ee);
    }

    public void writeFile(){

        try{


            String yourSystemPath = System.getProperty("jboss.server.data.dir");
            String filename = yourSystemPath+"/"+scenario+"-"+id+".csv";
            System.out.println("Appending sequence to file "+filename);
            FileWriter fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.println(this.toString());
            out.close();

        } catch (IOException e) {


        }

    }

    @Override
    public String toString(){
        String s = "";
        for (EndpointEvent ee : eeList){
            s+=ee.getUseCaseType()+","+ee.getTimestamp()+",";
        }
        return s;
    }

}
