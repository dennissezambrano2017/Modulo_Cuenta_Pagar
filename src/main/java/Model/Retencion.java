package Model;

import Controller.Conexion;
import DataView.FacturaDAO;
import java.time.LocalDate;

/**
 *
 * @author elect
 */
public class Retencion extends Conexion {

    private int id_retencion;
    private LocalDate fecha_emision; // fecha de la emición del comprobante
    private String n_comprobante_retencion; // numero de comprobante de retencion, o identiciación 
    private int id_factura_compra;  // referencia hacia la factura.
    private Factura factura;
    
    private int id_tipo_comprobante;  // El tipo de comprobante.

    //datos faltantes
    private String estado_retencion;
    private String razon_social;
    private float total;

    public Retencion() {
    }

    public Retencion(int id_retencion, LocalDate fecha_emision, String n_comprobante_retencion, int id_factura_compra, int id_tipo_comprobante, String estado, String razon_social, float total) {
        this.id_retencion = id_retencion;
        this.fecha_emision = fecha_emision;
        this.n_comprobante_retencion = n_comprobante_retencion;
        this.id_factura_compra = id_factura_compra;
        this.id_tipo_comprobante = id_tipo_comprobante;
        this.estado_retencion = estado;
        this.razon_social = razon_social;
        this.total = total;
    }

    public int getId_retencion() {
        return id_retencion;
    }

    public void setId_retencion(int id_retencion) {
        this.id_retencion = id_retencion;
    }

    public LocalDate getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(LocalDate fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getN_comprobante_retencion() {
        return n_comprobante_retencion;
    }

    public void setN_comprobante_retencion(String n_comprobante_retencion) {
        this.n_comprobante_retencion = n_comprobante_retencion;
    }

    public int getId_factura_compra() {
        return id_factura_compra;
    }
    
    

    public void setId_factura_compra(int id_factura_compra) {
        this.id_factura_compra = id_factura_compra;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public int getId_tipo_comprobante() {
        return id_tipo_comprobante;
    }

    public void setId_tipo_comprobante(int id_tipo_comprobante) {
        this.id_tipo_comprobante = id_tipo_comprobante;
    }

    public String getEstado_retencion() {
        return estado_retencion;
    }

    public void setEstado_retencion(String estado_retencion) {
        this.estado_retencion = estado_retencion;
    }

    

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }


    
    // traer la factura de la db
    public void getFacturaCompra() {
        //this.factura = Factura.getOneFactura(this.id_factura_compra);
        System.out.println("factura id: " + this.id_factura_compra);
        // trae la factura con el proveedor.
        this.setFactura(FacturaDAO.getOneFactura(this.id_factura_compra).GetdbProveedor());
    }
}
