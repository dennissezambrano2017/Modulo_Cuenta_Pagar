/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataView.BuscarProvDAO;
import Controller.AbonoProveedorManagedBean;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import Model.Proveedor;
import Model.Factura;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author ninat
 */
@ManagedBean(name = "BuscarMB")
@ViewScoped
public class BuscarProvManagedBean implements Serializable {

    private Proveedor proveedor;
    private BuscarProvDAO buscarprovDAO;
    private Factura factura;
    private List<Proveedor> listaProveedor;
    private List<Factura> listaFactura;
    private String nom;
    private String cod;
    private AbonoProveedorManagedBean abonoMB;

    public BuscarProvManagedBean() {
        proveedor = new Proveedor();
        listaProveedor = new ArrayList<>();
        listaFactura = new ArrayList<>();
        buscarprovDAO = new BuscarProvDAO();
        listaProveedor = buscarprovDAO.llenar();
        abonoMB = new AbonoProveedorManagedBean();
        factura = new Factura();
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public BuscarProvDAO getBuscarProvDAO() {
        return buscarprovDAO;
    }

    public void setBuscarProvDAO(BuscarProvDAO buscarprovDAO) {
        this.buscarprovDAO = buscarprovDAO;
    }

    public List<Proveedor> getListaProveedor() {
        return listaProveedor;
    }

    public void setListaProveedor(List<Proveedor> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }

    public void onRowSelect(SelectEvent<Proveedor> event) {
        String msg2 = event.getObject().getNombre();
        String msg3 = event.getObject().getRuc();
        System.out.print("Nombre: " + msg2);
        System.out.print("Ruc: " + msg3);
        setNom(msg2);
        setCod(msg3);
    }
    //Paola: Llenar Factura

    public void onRowSelectf(SelectEvent<Proveedor> event) {
        String msg2 = event.getObject().getNombre();
        String msg3 = event.getObject().getRuc();
        System.out.print("Nombre: " + msg2);
        System.out.print("Ruc: " + msg3);
        setNom(msg2);
        setCod(msg3);
        listaFactura = abonoMB.BuscarFactura(msg3);

    }

     public void onRowEdit(RowEditEvent<Factura> event) {
        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getNfactura()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
     public void onRowCancel(RowEditEvent<Factura> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getNfactura()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", 
                    "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    public void onRowDoubleClick(final SelectEvent event) {
    Object obj = (Object) event.getObject();
    // rest of your logic
    if (obj != null ) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "New:" + obj);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    public void Envio()
    {
        abonoMB.enviar(listaFactura);
    }

    public List<Factura> getListaFactura() {
        return listaFactura;
    }

    public void setListaFactura(List<Factura> listaFactura) {
        this.listaFactura = listaFactura;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public AbonoProveedorManagedBean getAbonoMB() {
        return abonoMB;
    }

    public void setAbonoMB(AbonoProveedorManagedBean abonoMB) {
        this.abonoMB = abonoMB;
    }

}
