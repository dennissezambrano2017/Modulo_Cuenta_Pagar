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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author ninat
 */
@ManagedBean(name = "facturaMB")
@ViewScoped
public class FacturaManagedBean implements Serializable {

    private Factura factura;
    private FacturaDAO facturaDAO;
    private List<Factura> listaFactura;
    private List<Factura> selectedFactura;
    private boolean check;


    public FacturaManagedBean() {
        factura = new Factura();
        listaFactura = new ArrayList<>();
        facturaDAO = new FacturaDAO();
        listaFactura = facturaDAO.llenar();

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

    public List<Factura> getSelectedFactura() {
        return selectedFactura;
    }

    public void setSelectedFactura(List<Factura> selectedFactura) {
        this.selectedFactura = selectedFactura;
    }
    
    
    public void abrirNuevo() {
        this.factura = new Factura();
    }
    
    public void insertarfactura(Factura factura) {
        System.out.print("ESTOY AQUI EN EL MANAGED");
        System.out.print("ruc: "+factura.getRuc());
        try {
            if("".equals(factura.getRuc()))
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Error al guardar"));
            else{
            this.facturaDAO.Insertar(factura);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Factura Guardada"));
            }
        } catch (Exception e) {
            System.out.println(e + "ERROR DAO");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Error al guardar"));
        }
        PrimeFaces.current().executeScript("PF('NewFactura').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-factura");
    }
    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean cheack) {
        this.check = cheack;
    }

    public void Registro() {
        String detail = check ? "Pago Autorizado" : "Pago no Autorizado";
        System.out.println(factura.getNfactura()+"-"+factura.getDescripcion()+"-"+detail);
        if (detail == "Pago Autorizado") {
//            facturaDAO.Autorizar(autorizarPago.sentencia(factura.getNfactura()));
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(detail));
        } else {
//            facturaDAO.Autorizar(autorizarPago.sentencia(factura.getNfactura()));
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(detail));
        }
    }
}
