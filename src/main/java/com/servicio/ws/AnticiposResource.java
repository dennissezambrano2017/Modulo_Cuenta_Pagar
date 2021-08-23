/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicio.ws;

import Model.Anticipo;
import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author elect
 */
@Path("anticipos")
@RequestScoped
public class AnticiposResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnticiposResource
     */
    public AnticiposResource() {
    }

    /**
     * Retrieves representation of an instance of com.servicio.ws.AnticiposResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        Anticipo anticipo = new Anticipo();
        anticipo.setDescripcion("hola khe ace");
        Gson gson = new Gson();
        return gson.toJson(anticipo);
    }

    /**
     * PUT method for updating or creating an instance of AnticiposResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
