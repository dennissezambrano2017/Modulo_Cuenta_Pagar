/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicio.ws;

import Controller.FacturaManagedBean;
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
 * @author ninat
 */
@Path("/facturas")
public class FacturaResource {
    
    FacturaManagedBean servicio = new FacturaManagedBean();
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<Factura> getFacturas(){
            return servicio.ListFactura();
        }
        
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Factura addFactura(Factura factura){
            return servicio.insert(factura);
        }
}
