/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicio.ws;

import DataView.AnticipoDAO;
import Model.Anticipo;
import Model.Mensaje;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
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
        List<Anticipo> anticipos = AnticipoDAO.getAllJson();
        Gson gson = new Gson();
        return gson.toJson(anticipos);
    }
    
    @GET
    @Path("/{anticipoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getOneAnticipo(@PathParam("anticipoId") String id) {
        Anticipo ant = AnticipoDAO.getOneAnticipo(id);
        Gson gson = new Gson();
        return gson.toJson(ant);
    }
    
    @DELETE
    @Path("/{anticipoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteOneAnticipo(@PathParam("anticipoId") String id) {
        String data = "";
        try {
            Anticipo anticipo = new Anticipo();
            anticipo.setId_anticipo(id);
            anticipo.deleteDB();
            data = String.format("{ \" %s \": \"%s\"}", "msg", "Anticipo eliminado correctamente");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            data = String.format("{ \" %s \": \" %s \"}", "error", "Error no se puedo eliminar el anticipo");
        }


        return data;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String PostOneAnticipo(String  content) {
        Gson gson = new Gson();
        
        System.out.println("Post One anticipo");
        System.out.println(content);
        String data = "";
        try {
            // Deserializamos los datos del cliente
            Anticipo ant = gson.fromJson(content, Anticipo.class);
            System.out.println("Descr: " + ant.getDescripcion());
            System.out.println("id antici: " + ant.getId_anticipo());
            System.out.println("id provee: " + ant.getId_proveedor());
            ant.InsertDB();
            
            data = String.format("{ \" %s \": \"%s\"}", "msg", "Anticipo guardado correctamente");
        } catch(JsonSyntaxException ex) {
            System.err.println(ex.getMessage());
            
            data = String.format("{ \" %s \": \" %s \"}", "error", "Error en el json");
        } 
        return data;
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
