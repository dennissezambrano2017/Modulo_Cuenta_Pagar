/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicio.ws;

import Controller.AbonoProveedorManagedBean;
import Controller.AutorizarPagoManagedBean;
import Model.AbonoProveedor;
import Model.Factura;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author PAOLA
 */
@Path("/autorizar")
public class AutorizarResource {

    AutorizarPagoManagedBean servicio = new AutorizarPagoManagedBean();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getFacturas() {
        List<Factura> lista = servicio.listFactura();
        Gson gson = new Gson();
        return gson.toJson(lista);

    }

    @GET
    @Path("/{nfactura}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFacturas(@PathParam("nfactura") String nfactura) {
        List<Factura> lista = servicio.buscDatos(nfactura);
        Gson gson = new Gson();
        return gson.toJson(lista);
    }
    
    @PUT
    @Path("/{nfactura}")
    @Produces(MediaType.APPLICATION_JSON)
    public String  autoFacturas(@PathParam("nfactura") String nfactura) {
        List<Factura> lista = servicio.autoPago(nfactura);
        Gson gson = new Gson();
        return gson.toJson(lista);
    }
    
    

}
