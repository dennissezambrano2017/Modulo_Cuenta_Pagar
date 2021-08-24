/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicio.ws;

import Controller.AbonoProveedorManagedBean;
import Model.AbonoProveedor;
import Model.Factura;
import java.time.LocalDate;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author PAOLA
 */

@Path("/pago")
public class PagoResource {
    
    AbonoProveedorManagedBean servicio = new AbonoProveedorManagedBean();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AbonoProveedor> getPagos(){
        return servicio.ListAbono();
    }
//    @GET
//    @Path("/3")
//    @Produces(MediaType.APPLICATION_XML)
//    public void buscar(){
//        
//    }
    
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Factura> addAbonoPro( List<Factura> abono){
//        return servicio.insertar(abono);
//    }
}
