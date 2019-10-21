package it.unifi.webapp.backend.rest.services.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class JsonResponseBuilder {


    private Gson gson;
    private JsonElement jsonElement;
    private Gson gsonBuilder;

    public JsonResponseBuilder(){ }

    public void addField(String name, Object o){

        this.jsonElement.getAsJsonObject().addProperty(name, gson.toJson(o, o.getClass()));
    }


    public void addField(String name, String o){
        this.jsonElement.getAsJsonObject().addProperty(name, o);
    }

    public void addField(String name, int o){
        this.jsonElement.getAsJsonObject().addProperty(name, o);
    }

    public String getJson(){
        return gson.toJson(jsonElement);
    }

    public void createResponse(){
        gson = new Gson();
        jsonElement = gson.toJsonTree(new Object());
        gsonBuilder = new GsonBuilder().create();
    }

}
