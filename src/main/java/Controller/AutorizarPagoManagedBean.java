/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataView.FacturaDAO;
import Model.Factura;
import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

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
    private List<Factura> listarDatos;
    private List<Factura> detalleDatos;
    private boolean check;

    /**
     * Creates a new instance of AutorizarPago
     */
    public AutorizarPagoManagedBean() {
        facturaDAO = new FacturaDAO();
        factura = new Factura();
        listarDatos = new ArrayList<>();
        detalleDatos = new ArrayList<>();
        this.listarDatos.clear();
        this.listarDatos = this.facturaDAO.llenar();
    }
    
    public List<Factura> listFactura(){
        this.listarDatos.clear();
        return this.listarDatos = this.facturaDAO.llenar();        
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

    public List<Factura> getListarDatos() {
        return listarDatos;
    }

    public void setListarDatos(List<Factura> listarDatos) {
        this.listarDatos = listarDatos;
    }

    public List<Factura> getDetalleDatos() {
        return detalleDatos;
    }

    public void setDetalleDatos(List<Factura> detalleDatos) {
        this.detalleDatos = detalleDatos;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean cheack) {
        this.check = cheack;
    }
    public List<Factura> buscDatos(String date){
        listarDatos.clear();
        return listarDatos=facturaDAO.llenarFact(date);
    }
    public void cargarDatos(Factura factura) {
        String dato = factura.getNfactura();
        this.factura.setNombre(factura.getNombre());
        this.factura.setNfactura(factura.getNfactura());
        this.factura.setDescripcion(factura.getDescripcion());
        this.factura.setImporte(factura.getImporte());
        this.factura.setFecha(factura.getFecha());
        this.factura.setVencimiento(factura.getVencimiento());
        this.factura.setRuc(facturaDAO.Buscar(dato));
        this.factura.setPagado(facturaDAO.buscarPagado(dato));
        this.factura.setAux(sumfechas());
        detalleDatos.clear();
        detalleDatos = facturaDAO.llenarDetalle(dato);
    }
    
    public void cargarAutor(Factura factura) {
        this.factura.setNfactura(factura.getNfactura());
    }

    public int sumfechas() {
        Duration diff = Duration.between(factura.getFecha().atStartOfDay(), factura.getVencimiento().atStartOfDay());
        long diffDays = diff.toDays();
        int dia = (int) diffDays;
        return dia;
    }

    public void reset() {
        PrimeFaces.current().resetInputs("form:outputvisu, form:dt-detalle");
        detalleDatos.clear();
    }
    public List<Factura> autoPago(String nfactura){
        this.facturaDAO.AutorizarPago(nfactura);
        listarDatos.clear();
        return listarDatos = facturaDAO.llenar();
    }

    public void autorizarPago() {
        this.facturaDAO.AutorizarPago(factura.getNfactura());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_INFO,"Exito","Pago Autorizado a Factura #" + factura.getNfactura()));
        listarDatos.clear();
        listarDatos = facturaDAO.llenar();
        PrimeFaces.current().ajax().update("form:dt-facturas",":form:messages");
    }
}
