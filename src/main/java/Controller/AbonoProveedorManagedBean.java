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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.event.SelectEvent;

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
        listaProveedor = buscarprovDAO.llenar();
        abonoDAO = new AbonoProveedorDAO();
        listaAbonos = abonoDAO.llenar();
        factura = new Factura();
    }

    public void mostrar() {
        listaAbonos = abonoDAO.llenar();
        System.out.println(listaAbonos);
    }

    public void mostrarPago() {
        listaFactura = facturaDAO.llenar();
        System.out.println(listaFactura);
    }

    public void enviar(List<Factura> listafactura) {
        this.abonoDAO = new AbonoProveedorDAO(abonoproveedor);
        int result;
        System.out.println(tipoPago.getDescripcion() + "--"
                + tipoBanco.getDescrpcion() + "--" + buscarprovDAO.getProveedor().getRuc());

        try {
            this.abonoDAO.insertar(abonoproveedor.getSentencia(tipoPago.getDescripcion(),
                    tipoBanco.getDescrpcion(), abonoproveedor.getRuc(), abonoproveedor.getFecha()));
//            try {
//                this.listaFactura = abonoDAO.llenar(abonoproveedor.BuscarFactura(abonoproveedor.getRuc()));
//                System.out.println(this.listaFactura.size() + "--");
//                int index = 0;
//                while (index > getListaFactura().size()) {
//                    this.abonoDAO.insertar(detalleAbono.getSentencia(listaFactura.get(index).getNfactura()));
//                    index++;
//                }
//                System.out.println("EXITO");
//            } catch (Exception ex) {
//                System.err.println(ex + ":Error en guardar los datos en BD");
//            }
        } catch (Exception e) {
            System.out.println(e + "Error en registrar Cabezera Abono");
        }
    }

    public int InsertDetail(String ruc) {
        System.out.println("Estoy buscandi factura");
        int result = 0;
        try {
            this.listaFactura = abonoDAO.llenar(abonoproveedor.BuscarSentenciaFactura(ruc));
            System.out.println(listaFactura.size() + "--");
            int index = 0;
            while (index > getListaFactura().size()) {
                this.abonoDAO.insertar(detalleAbono.getSentencia(listaFactura.get(index).getNfactura()));
                index++;
            }
            result = 1;
            System.out.println("EXITO");
            return result;
        } catch (Exception ex) {
            result = 0;
            System.err.println(ex + ":Error en guardar los datos en BD");
        }

        return result;
    }

    public List<Factura> BuscarFactura(String ruc) {
        System.out.print(getCod()+"ando aqui");
        this.listaFactura = abonoDAO.llenar(abonoproveedor.BuscarSentenciaFactura(ruc));
//        
        return listaFactura;
    }
    
    public void Factura(){
        FacesContext =FacesContext.getCurrentInstance();
        Map<String, String> parametros= FacesContext.getExternalContext().getRequestParameterMap();
        int accion=0;
        switch (accion){
            case 1: this.listaProveedor = buscarprovDAO.llenar();
            case 2: this.listaFactura = abonoDAO.llenar(abonoproveedor.BuscarSentenciaFactura(getCod()));
        }
//         return listaFactura = abonoDAO.llenar(abonoproveedor.BuscarSentenciaFactura());
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

    public List<Proveedor> getListaProveedor() {
        return listaProveedor;
    }

    public void setListaProveedor(List<Proveedor> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }

    public void deleteProduct() {
        listaFactura.remove(factura);
        factura = null;
    }

    public String getNfactura() {
        return nfactura;
    }

    public void setNfactura(String nfactura) {
        this.nfactura = nfactura;
    }

    public float getPago() {
        return pago;
    }

    public void setPago(float pago) {
        this.pago = pago;
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
     

    
}
