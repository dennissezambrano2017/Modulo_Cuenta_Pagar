/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataView.BuscarProvDAO;
import DataView.AbonoProveedorDAO;
import Model.AbonoProveedor;
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
    private List<Factura> listafactura;
    private String nom;
    private String cod;
    private String Nfactura;
    private float Pago;
    private AbonoProveedorManagedBean abonoMB;
    private AbonoProveedor abonoproveedor;
    private AbonoProveedorDAO abonoDAO;

    public BuscarProvManagedBean() {
        proveedor = new Proveedor();
        listaProveedor = new ArrayList<>();
        listafactura = new ArrayList<>();
        buscarprovDAO = new BuscarProvDAO();
        listaProveedor = buscarprovDAO.llenar();
        abonoMB = new AbonoProveedorManagedBean();
        factura = new Factura();
        abonoDAO = new AbonoProveedorDAO();
        abonoproveedor = new AbonoProveedor();
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
        listafactura = abonoDAO.llenarDatos(abonoproveedor.BuscarSentenciaFactura(msg3));        
    }

    public void onRowDoubleClick(final SelectEvent event) {
        Object obj = (Object) event.getObject();
        // rest of your logic
        if (obj != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "New:" + obj);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

//    public void Envio() {
//        abonoMB.enviar(listaFactura);
//    }

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

    public String getNfactura() {
        return Nfactura;
    }

    public void setNfactura(String Nfactura) {
        this.Nfactura = Nfactura;
    }

    public float getPago() {
        return Pago;
    }

    public void setPago(float Pago) {
        this.Pago = Pago;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public AbonoProveedor getAbonoproveedor() {
        return abonoproveedor;
    }

    public void setAbonoproveedor(AbonoProveedor abonoproveedor) {
        this.abonoproveedor = abonoproveedor;
    }

    public AbonoProveedorDAO getAbonoDAO() {
        return abonoDAO;
    }

    public void setAbonoDAO(AbonoProveedorDAO abonoDAO) {
        this.abonoDAO = abonoDAO;
    }

    //Paola
    public void onRowEdit(RowEditEvent<Factura> event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Se Modifico la cantidad"));
    }

    public void onRowCancel(RowEditEvent<Factura> event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Se Cancelo la modificación"));
    }
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }


    public List<Factura> getListafactura() {
        return listafactura;
    }

    public void setListafactura(List<Factura> listafactura) {
        this.listafactura = listafactura;
    }

    public BuscarProvDAO getBuscarprovDAO() {
        return buscarprovDAO;
    }

    public void setBuscarprovDAO(BuscarProvDAO buscarprovDAO) {
        this.buscarprovDAO = buscarprovDAO;
    }

}
