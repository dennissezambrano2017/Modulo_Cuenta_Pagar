/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataView.FacturaDAO;
import DataView.BuscarProvDAO;
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
    private BuscarProvDAO busprovDAO;
    private List<Factura> listaFactura;
    private List<Factura> selectedFactura;
    private boolean check;
    private boolean value;

    public FacturaManagedBean() {
        factura = new Factura();
        listaFactura = new ArrayList<>();
        facturaDAO = new FacturaDAO();
        listaFactura = facturaDAO.llenar();
        busprovDAO = new BuscarProvDAO();
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

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public List<Factura> check() {
        String summary = value ? "Pendientes" : "Todas";
        if (value) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
            listaFactura = facturaDAO.llenarP();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
            listaFactura = facturaDAO.llenar();
        }
        return listaFactura;
    }

    public void abrirNuevo() {
        this.factura = new Factura();
    }

    public void cargarEditar(Factura factura) {
        String dato = factura.getNfactura();
        this.factura.setNombre(factura.getNombre());
        this.factura.setNfactura(factura.getNfactura());
        this.factura.setDescripcion(factura.getDescripcion());
        this.factura.setImporte(factura.getImporte());
        this.factura.setFecha(factura.getFecha());
        this.factura.setVencimiento(factura.getVencimiento());
        this.factura.setRuc(busprovDAO.Buscar(dato));
        this.factura.setPagado(facturaDAO.buscarPagado(dato));
    }

    public void reset() {
        PrimeFaces.current().resetInputs("form:outputnuevo");
    }

    //Diana: insertar nueva Factura
    public void insertarfactura() {
        System.out.print("ESTOY AQUI EN EL MANAGED INSERTAR");
        System.out.print("ruc: " + factura.getRuc());
        if (fechas()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fecha es mayor que vencimiento"));
        } else {
            if (factura.getImporte() < factura.getPagado()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Importe es menor que pagado"));
            } else {
                try {
                    if ("".equals(factura.getRuc())) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error al guardar"));
                    } else {
                        this.facturaDAO.Insertar(factura);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Factura Guardada"));
                    }
                } catch (Exception e) {
                    System.out.println("ERROR DAO: " + e);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error al guardar"));
                }
                PrimeFaces.current().executeScript("PF('newFactura').hide()");
                PrimeFaces.current().executeScript("location.reload()");
            }
        }
    }

    //Diana Actualizar factura
    public void editarfactura() {
        System.out.print("ESTOY AQUI EN EL MANAGED ACTUALIZAR");
        System.out.print("ruc: " + factura.getRuc());
        if (fechas()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fecha es mayor que vencimiento"));
        } else {
            if (factura.getImporte() < factura.getPagado()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Importe es menor que pagado"));
            } else {
                try {
                    if ("".equals(factura.getRuc())) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error al guardar"));
                    } else {
                        this.facturaDAO.Actualizar(factura);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Factura Guardada"));
                    }
                } catch (Exception e) {
                    System.out.println("ERROR DAO: " + e);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error al guardar"));
                }
                PrimeFaces.current().executeScript("PF('editFactura').hide()");
                PrimeFaces.current().executeScript("location.reload()");
            }
        }
    }

    public Boolean fechas() {

        int year1 = Integer.parseInt(((factura.getFecha()).toString()).substring(0, 4));
        int mes1 = Integer.parseInt(((factura.getFecha()).toString()).substring(5, 7));
        int dia1 = Integer.parseInt(((factura.getFecha()).toString()).substring(8, 10));
        int year2 = Integer.parseInt(((factura.getVencimiento()).toString()).substring(0, 4));
        int mes2 = Integer.parseInt(((factura.getVencimiento()).toString()).substring(5, 7));
        int dia2 = Integer.parseInt(((factura.getVencimiento()).toString()).substring(8, 10));

        if (year1 > year2) {
            return true;
        } else {
            if (year1 == year2) {
                if (mes1 > mes2) {
                    return true;
                } else {
                    if (mes1 == mes2) {
                        if (dia1 > dia2) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
    }
    
     public void dhFactura(Factura factura) {
        System.out.print("HOLA SI ENTRE DELETE HABILITAR 1");
        this.facturaDAO.dhabilitar(factura.getNfactura());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Deshabilitada factura: " + factura.getNfactura()));
        PrimeFaces.current().executeScript("location.reload()");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedFactura()) {
            int size = this.selectedFactura.size();
            return size > 1 ? size + " products selected" : "1 product selected";
        }
        return "Delete";
    }

    public boolean hasSelectedFactura() {
        return this.selectedFactura != null && !this.selectedFactura.isEmpty();
    }

    public void deleteSelectedFacturas() {
        System.out.print("HOLA SI ENTRE DELETE HABILITAR 2");
        this.facturaDAO.habilitar(this.listaFactura);
        this.selectedFactura = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages");
        PrimeFaces.current().executeScript("PF('dtFactura').clearFilters()");
    }

    //ESTO ES DE PAOLA
    //    public boolean valores(){
//        float importe = factura.getImporte();
//        float
//    }
    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean cheack) {
        this.check = cheack;
    }

    public void Registro() {
        String detail = check ? "Pago Autorizado" : "Pago no Autorizado";
        System.out.println(factura.getNfactura() + "-" + factura.getDescripcion() + "-" + detail);
        if (detail == "Pago Autorizado") {
//            facturaDAO.Autorizar(autorizarPago.sentencia(factura.getNfactura()));
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(detail));
        } else {
//            facturaDAO.Autorizar(autorizarPago.sentencia(factura.getNfactura()));
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(detail));
        }
    }
}
