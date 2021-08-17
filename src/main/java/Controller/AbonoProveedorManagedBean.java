/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataView.AbonoProveedorDAO;
import DataView.BuscarProvDAO;
import DataView.FacturaDAO;
import Model.AbonoProveedor;
import Model.DetalleAbono;
import Model.TipoPago;
import Model.TipoBanco;
import Model.Factura;
import Model.Proveedor;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import javax.faces.event.ActionListener;

/**
 *
 * @author PAOLA
 */
@ManagedBean(name = "abonoProveedorMB")
@ViewScoped
public class AbonoProveedorManagedBean implements Serializable {

    private AbonoProveedor abonoproveedor;
    private DetalleAbono detalleAbono;
    private TipoPago tipoPago;
    private TipoBanco tipoBanco;
    private Proveedor proveedor;
    private AbonoProveedorDAO abonoDAO;
    private FacturaDAO facturaDAO;
    private List<AbonoProveedor> listaAbonos;
    private List<Factura> listaFactura;
    private List<Factura> listaSecFactura;
    private List<Proveedor> listaProveedor;
    private BuscarProvDAO buscarprovDAO;
    private Factura factura;
    private String nfactura;
    private float pago;
    private String nom;
    private String cod;
    private FacesContext FacesContext;

    public AbonoProveedorManagedBean() {
        abonoproveedor = new AbonoProveedor();
        detalleAbono = new DetalleAbono();
        tipoPago = new TipoPago();
        tipoBanco = new TipoBanco();
        proveedor = new Proveedor();
        listaAbonos = new ArrayList<>();
        listaFactura = new ArrayList<>();
        listaProveedor = new ArrayList<>();
        buscarprovDAO = new BuscarProvDAO();
        abonoDAO = new AbonoProveedorDAO();
        factura = new Factura();
        listaAbonos = abonoDAO.llenarDatos(abonoproveedor.sentenciaMostrar());
        listaProveedor = abonoDAO.llenarProveedor();
        
    }

    //Metodos 
    public void mostrar() {
        this.listaAbonos = abonoDAO.llenarDatos(abonoproveedor.sentenciaMostrar());
    }

    public void mostrar(String ruc) {
        this.listaFactura.clear();
        this.listaFactura = abonoDAO.llenarFacturas(abonoproveedor.BuscarSentenciaFactura(ruc));
    }

    public void mostraProveedor() {
        this.listaProveedor = abonoDAO.llenarProveedor();
    }

    public void onRowSelectf(SelectEvent<Proveedor> event) {
        String msg2 = event.getObject().getNombre();
        String msg3 = event.getObject().getRuc();
        System.out.print("Nombre: " + msg2);
        System.out.print("Ruc: " + msg3);
        setNom(msg2);
        setCod(msg3);
        this.listaFactura.clear();
        this.listaFactura = abonoDAO.llenarFacturas(abonoproveedor.BuscarSentenciaFactura(msg3));
        this.listaSecFactura =this.listaFactura;
//        int index = 0;
//        while (index < listaFactura.size()) {
//            listaSecFactura.add(new Factura(listaFactura.get(index).getNfactura(),
//                    listaFactura.get(index).getImporte(), listaFactura.get(index).getFecha(),
//                    listaFactura.get(index).getFecha(), listaFactura.get(index).getVencimiento(),
//                    listaFactura.get(index).getPendiente()));
//        }
    }

    public void Cargar(Factura factura) {
        int n = 0;
        System.out.println("hola" + n);
        this.factura.setNfactura(factura.getNfactura());
        this.factura.setFecha(factura.getFecha());
        this.factura.setVencimiento(factura.getVencimiento());
        this.factura.setPagado(factura.getPagado());

    }
    public void Enviar(){
        this.factura= new Factura();
        System.out.print("ESTOY AQUI EN EL MANAGED ACTUALIZAR");
        System.out.print("ruc: " + factura.getRuc());
        
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

    public List<AbonoProveedor> getListaAbonos() {
        return listaAbonos;
    }

    public void setListaAbonos(List<AbonoProveedor> listaAbonos) {
        this.listaAbonos = listaAbonos;
    }

    public List<Factura> getListaFactura() {
        return listaFactura;
    }

    public void setListaFactura(List<Factura> listaFactura) {
        this.listaFactura = listaFactura;
    }

    public List<Proveedor> getListaProveedor() {
        return listaProveedor;
    }

    public void setListaProveedor(List<Proveedor> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }

    public Factura getFactura() {
        System.err.println(factura);
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public String getNfactura() {
        return nfactura;
    }

    public void setNfactura(String nfactura) {
        this.nfactura = nfactura;
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

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public float getPago() {
        return pago;
    }

    public void setPago(float pago) {
        this.pago = pago;
    }

    public AbonoProveedor getAbonoproveedor() {
        return abonoproveedor;
    }

    public void setAbonoproveedor(AbonoProveedor abonoproveedor) {
        this.abonoproveedor = abonoproveedor;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public TipoBanco getTipoBanco() {
        return tipoBanco;
    }

    public void setTipoBanco(TipoBanco tipoBanco) {
        this.tipoBanco = tipoBanco;
    }

    public FacturaDAO getFacturaDAO() {
        return facturaDAO;
    }

    public void setFacturaDAO(FacturaDAO facturaDAO) {
        this.facturaDAO = facturaDAO;
    }

    public List<Factura> getListaSecFactura() {
        return listaSecFactura;
    }

    public void setListaSecFactura(List<Factura> listaSecFactura) {
        this.listaSecFactura = listaSecFactura;
    }
    

}
