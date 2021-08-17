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
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author ninat
 */
@ManagedBean(name = "facturaMB")
@SessionScoped
public class FacturaManagedBean implements Serializable {

    private Factura factura;
    private FacturaDAO facturaDAO = new FacturaDAO();
    private BuscarProvDAO busprovDAO = new BuscarProvDAO();
    private List<Factura> listaFactura;
    private List<Factura> detalleFactura;
    private boolean check;
    private float datoImporte;
    private String datoDetalle;

    //Constructor
    public FacturaManagedBean() {
        factura = new Factura();
        listaFactura = new ArrayList<>();
      //  detalleFactura = new ArrayList<>();
        check = true;
        System.out.print("ESTOY AQUI EN EL MANAGED NUEVO");
        this.listaFactura.clear();
        this.listaFactura = this.facturaDAO.llenar();
    }

//    public void mostrar() {
//        check = true;
//    }
    
    // Getter and Setter
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

    public List<Factura> getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(List<Factura> detalleFactura) {
        this.detalleFactura = detalleFactura;
    }
    

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean cheack) {
        this.check = cheack;
    }

    //Diana: insertar nueva Factura
    public void insertarfactura() {
        System.out.print("ESTOY AQUI EN EL MANAGED INSERTAR");
        System.out.print("Cantidad detalle: "+detalleFactura.size());
        System.out.print("ruc: " + factura.getRuc());
        if (fechas()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fecha es mayor que vencimiento"));
        } else {
            if (factura.getImporte() < factura.getPagado()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Importe es menor que pagado"));
            } else {
                try {
                    if ("".equals(factura.getRuc())) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al guardar"));
                    } else {
                        //facturaDAO.Insertar(factura);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Factura Guardada"));
                    }
                } catch (Exception e) {
                    System.out.println("ERROR DAO: " + e);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR AL GUARDAR"));
                }
                PrimeFaces.current().executeScript("PF('newFactura').hide()");
                listaFactura.clear();
                check = true;
                listaFactura = facturaDAO.llenarP("1");
                PrimeFaces.current().ajax().update("form:dt-factura", "form:slcbtn");
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
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Factura Actualizada"));
                    }
                } catch (Exception e) {
                    System.out.println("ERROR DAO: " + e);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR AL GUARDAR"));
                }
                PrimeFaces.current().executeScript("PF('editFactura').hide()");
                //PrimeFaces.current().executeScript("location.reload()");
                listaFactura.clear();
                listaFactura = facturaDAO.llenarP("1");
                PrimeFaces.current().ajax().update("form:dt-factura", "form:slcbtn");
            }
        }
    }

    //Cardar datos para actualizar
    public void cargarEditar(Factura factura) {
        System.out.print(factura.getNfactura());
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

    //Diana: Habilitar y Deshabilitar
    public void dhFactura(Factura factura) {
        System.out.print("HOLA SI ENTRE DELETE HABILITAR 1");
        this.facturaDAO.dhabilitar(factura.getNfactura());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Deshabilitada factura: " + factura.getNfactura()));
        listaFactura.clear();
        listaFactura = facturaDAO.llenarP("1");
        PrimeFaces.current().ajax().update("form:dt-factura");
    }

    //Mostrar tablas habilitadas y deshabilitadas
    public void habTabla() {
        listaFactura.clear();
        if (check) {
            this.listaFactura = facturaDAO.llenarP("1");
        } else {

            this.listaFactura = facturaDAO.llenarP("0");
        }
        PrimeFaces.current().ajax().update("form:dt-factura");
    }

    //Funciones apartes
    public void abrirNuevo() {
        this.factura = new Factura();
    }

    public void hola() {
        System.out.print("hola");
    }

    public void reset() {
        System.out.print("sie ntre al reset");
        PrimeFaces.current().resetInputs("form:outputnuevo, form:dt-detalle");
        detalleFactura.clear();
    }

    //Comparación de fechas
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
    
    //DETALLE FACTURA


    public float getDatoImporte() {
        return datoImporte;
    }

    public void setDatoImporte(float datoImporte) {
        this.datoImporte = datoImporte;
    }

    public String getDatoDetalle() {
        return datoDetalle;
    }

    public void setDatoDetalle(String datoDetalle) {
        this.datoDetalle = datoDetalle;
    }
    
    
    
    public void onRowEdit(RowEditEvent<Factura> event) {
        Factura f = (Factura) event.getObject();
        f.setImporte(datoImporte);
        f.setDetalle(datoDetalle);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Edited"));
    }

    public void onRowCancel(RowEditEvent<Factura> event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Edit Cancelled"));
    }

    public void onAddNew() {
        // Add one new product to the table:
        System.out.print("Cantidad detalle: "+detalleFactura.size());
        Factura newFactura = new Factura(0,"Detalle factura");
        detalleFactura.add(newFactura);
        PrimeFaces.current().ajax().update("form:dt-detalle");
        System.out.print("Cantidad detalle 2: "+detalleFactura.size());
        FacesMessage msg = new FacesMessage("New Product added");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    
    
    
    

    //ESTO ES DE PAOLA
    //    public boolean valores(){
//        float importe = factura.getImporte();
//        float
//    }
    public void llenar() {
        this.listaFactura.clear();
        this.listaFactura = this.facturaDAO.llenar();
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
