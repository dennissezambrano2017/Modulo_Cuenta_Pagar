/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataView.AbonoProveedorDAO;
import DataView.FacturaDAO;
import Model.AbonoProveedor;
import Model.DetalleAbono;
import Model.TipoPago;
import Model.TipoBanco;
import Model.Factura;
import Model.Proveedor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

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
    private Factura factura;

    public AbonoProveedorManagedBean() {
        abonoproveedor = new AbonoProveedor();
        detalleAbono = new DetalleAbono();
        tipoPago = new TipoPago();
        tipoBanco = new TipoBanco();
        proveedor = new Proveedor();
        listaAbonos = new ArrayList<>();
        listaFactura = new ArrayList<>();
        abonoDAO = new AbonoProveedorDAO();
        listaAbonos = abonoDAO.llenar();
        factura= new Factura();


    }

    public void mostrar() {
        listaAbonos = abonoDAO.llenar();
        System.out.println(listaAbonos);
    }

    public void mostrarPago() {
        listaFactura = facturaDAO.llenar();
        System.out.println(listaFactura);
    }

    public void enviar() {
        this.abonoDAO = new AbonoProveedorDAO(abonoproveedor);
        System.out.println(tipoPago.getDescripcion() + "--" + tipoBanco.getDescrpcion() + "--" + abonoproveedor.getRuc());
        try {
            abonoDAO.insertar(abonoproveedor.getSentencia(tipoPago.getDescripcion(), tipoBanco.getDescrpcion(), abonoproveedor.getRuc()));
             System.out.println("Si entro10");
            try {
                System.out.println("Si entro1"+ factura.getNfactura());
                int index=0;
                while(index>listaFactura.size()){
                    System.out.println("Si entro2");
                    System.out.println(factura.getNfactura()+"-"+factura.getFecha()+"-"+
                            factura.getVencimiento()+"-"+factura.getImporte()+"-"+
                            factura.getPendiente()+"-"+factura.getPagado());
                }
//                this.abonoDAO.insertar(detalleAbono.getSentencia(proveedor.getCodigo()));
//                listaAbonos = abonoDAO.llenar();
//                System.out.println("EXITO");
                ActualizarFilas();
            } catch (Exception e) {
                System.out.println(e + " Error en registrar Detalle Abono");
            }
        } catch (Exception e) {
            System.out.println(e + "Error en registrar Cabezera Abono");
        }
    }
    
    public List<Factura> BuscarFactura( String ruc){
        System.out.println("Estoy buscandi factura");
        listaFactura=abonoDAO.llenar(abonoproveedor.BuscarFactura(ruc));
        System.out.println(listaFactura.size()+"--");
        return listaFactura;
    }

    public void ActualizarFilas() {
        listaAbonos = abonoDAO.llenar();

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

    public List<AbonoProveedor> getListaAbonos() {
        return listaAbonos;
    }

    public void setListaAbonos(List<AbonoProveedor> listaAbonos) {
        this.listaAbonos = listaAbonos;
    }

    public DetalleAbono getDetalleAbono() {
        return detalleAbono;
    }

    public void setDetalleAbono(DetalleAbono detalleAbono) {
        this.detalleAbono = detalleAbono;
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

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<Factura> getListaFactura() {
        return listaFactura;
    }

    public void setListaFactura(List<Factura> listaFactura) {
        this.listaFactura = listaFactura;
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
    
    
}
