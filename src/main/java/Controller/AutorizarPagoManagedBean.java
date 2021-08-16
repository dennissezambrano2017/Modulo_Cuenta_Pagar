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
import javax.inject.Named;

/**
 * /**
 *
 * @author PAOLA
 */
//@Named(value="uuu")
@ManagedBean(name = "autorizarPagorMB")
@ViewScoped
public class AutorizarPagoManagedBean implements Serializable {

    private FacturaDAO facturaDAO;
    private Factura factura;
    private List<Factura> listaFactura;
    private boolean check;

    /**
     * Creates a new instance of AutorizarPago
     */
    public AutorizarPagoManagedBean() {
        facturaDAO = new FacturaDAO();
        factura = new Factura();
        listaFactura = new ArrayList<>();
        listaFactura = facturaDAO.llenar();
    }

    public void mostrar() {
        listaFactura = facturaDAO.llenar();
        System.out.println(listaFactura.size() + "holis");
    }

    public void insertarfactura() {
        try {
        } catch (Exception e) {
            System.out.println(e + "ERROR DAO");
        }
    }

    public FacturaDAO getFacturaDAO() {
        return facturaDAO;
    }

    public void setFacturaDAO(FacturaDAO facturaDAO) {
        this.facturaDAO = facturaDAO;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<Factura> getListaFactura() {
        return listaFactura;
    }

    public void setListaFactura(List<Factura> listaFactura) {
        this.listaFactura = listaFactura;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean cheack) {
        this.check = cheack;
    }

    public void Registro(String estado) {
        String detail = check ? "Pago Autorizado" : "Pago no Autorizado";
        if (detail == "Pago Autorizado") {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(detail));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(detail));
        }
    }
}
