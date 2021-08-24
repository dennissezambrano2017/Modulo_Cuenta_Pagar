/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicio.ws;

import Controller.AbonoProveedorManagedBean;
import Model.AbonoProveedor;
import com.google.gson.Gson;
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
    public String getFacturas() {
        List<AbonoProveedor> lista = servicio.ListAbono();
        Gson gson = new Gson();
        return gson.toJson(lista);

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AbonoProveedor addFactura(AbonoProveedor abono) {
        return servicio.insert(abono);
    }

}
