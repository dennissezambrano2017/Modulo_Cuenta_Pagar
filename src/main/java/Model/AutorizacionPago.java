/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author PAOLA
 */
public class AutorizacionPago {
    private String nfactura;
    private String descripcion;
    private float importe;
    private LocalDate fecha;
    private LocalDate vencimiento;
    private String nombreProveedor;

    public AutorizacionPago(String nfactura, String descripcion, float importe, LocalDate fecha, LocalDate vencimiento, String nombreProveedor) {
        this.nfactura = nfactura;
        this.descripcion = descripcion;
        this.importe = importe;
        this.fecha = fecha;
        this.vencimiento = vencimiento;
        this.nombreProveedor = nombreProveedor;
    }

    public String getNfactura() {
        return nfactura;
    }

    public void setNfactura(String nfactura) {
        this.nfactura = nfactura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDate getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(LocalDate vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }
    
    
    
}
