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
import org.primefaces.event.RowEditEvent;

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
    private List<Factura> listafactura;

    public AbonoProveedorManagedBean() {
        abonoproveedor = new AbonoProveedor();
        detalleAbono = new DetalleAbono();
        tipoPago = new TipoPago();
        tipoBanco = new TipoBanco();
        proveedor = new Proveedor();
        listaAbonos = new ArrayList<>();
        abonoDAO = new AbonoProveedorDAO();
        listaAbonos = abonoDAO.llenar();

    }

    public void mostrar() {
        listaAbonos = abonoDAO.llenar();
        System.out.println(listaAbonos);
    }

    public void mostrarPago() {
        listafactura = facturaDAO.llenar();
        System.out.println(listafactura);
    }

    public void enviar() {
        this.abonoDAO = new AbonoProveedorDAO(abonoproveedor);
        System.out.println(tipoPago.getDescripcion() + "--" + tipoBanco.getDescrpcion() + "--" + proveedor.getCodigo());
        if (this.abonoDAO.insertar(abonoproveedor.getSentencia(tipoPago.getDescripcion(), tipoBanco.getDescrpcion(), proveedor.getCodigo())) > 0) {
            this.abonoDAO = new AbonoProveedorDAO(detalleAbono);
            if (this.abonoDAO.insertar(detalleAbono.getSentencia(proveedor.getCodigo())) > 0) {
                listaAbonos = abonoDAO.llenar();
                System.out.println("EXITO");
                ActualizarFilas();
            }
        }
        else{
            System.out.println("No se registro");
        }
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

}
