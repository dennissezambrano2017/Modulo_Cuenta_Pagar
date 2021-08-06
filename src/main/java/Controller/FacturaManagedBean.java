/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataView.FacturaDAO;
import Model.Factura;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ninat
 */
@ManagedBean(name = "facturaMB")
@ViewScoped
public class FacturaManagedBean implements Serializable{
    private Factura factura;
    private FacturaDAO facturaDAO;
    private List<Factura> listaFactura;
    
    public FacturaManagedBean() {
        factura = new Factura();
        listaFactura = new ArrayList<>();
        facturaDAO = new FacturaDAO();
        listaFactura = facturaDAO.llenar();
     
    }
    public void mostrar()
    {
        listaFactura = facturaDAO.llenar();
        System.out.println(listaFactura.size()+"holis");
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public FacturaDAO getFacturaDAO() {
        return facturaDAO;
    }

    public void setFacturaDAO(FacturaDAO facturaDAO) {
        this.facturaDAO = facturaDAO;
    }

    public List<Factura> getListaFactura() {
        return listaFactura;
    }

    public void setListaFactura(List<Factura> listaFactura) {
        this.listaFactura = listaFactura;
    }
    
    public void insertarfactura(Factura factura) {
        System.out.print("ESTOY AQUI EN EL MANAGED");
        System.out.print("Ruc: "+factura.getRuc());
          try {
               this.facturaDAO.Insertar(factura);   
              
          } catch (Exception e) {
               System.out.println(e+"ERROR DAO");
          }
     }
}