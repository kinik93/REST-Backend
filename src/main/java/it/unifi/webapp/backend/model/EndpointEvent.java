package it.unifi.webapp.backend.model;

import javax.persistence.*;


public class EndpointEvent {

    private String useCaseType;
    private long timestamp;
    private int eventType;
    public EndpointEvent(){}
    public EndpointEvent(String useCaseType, long timestamp, int eventType){
        this.useCaseType = useCaseType;
        this.timestamp = timestamp;
        this.eventType = eventType;
    }

    public int getEventType() {
        return eventType;
    }

    public String getUseCaseType() {
        return useCaseType;
    }

    public void setUseCaseType(String useCaseType) {
        this.useCaseType = useCaseType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString(){
        return "Logging event: "+this.useCaseType+" - time:"+timestamp;
    }
}
