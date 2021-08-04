/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author elect
 * 
 * Esta class sera utilizada para, mostrar infomación de la factura en los
 * reportes que genere el sistema.
 */
public class ReporteFactura {
    
    private LocalDate FechaAqusicion;
    private String Proveedor;
    private String NumeroFactura;
    private float ValorFactura;
    private LocalDate FechaVencimiento;
    private String TipoFactura;

    public ReporteFactura(LocalDate FechaAqusicion, String Proveedor, String NumeroFactura, float ValorFactura, LocalDate FechaVencimiento, String TipoFactura) {
        this.FechaAqusicion = FechaAqusicion;
        this.Proveedor = Proveedor;
        this.NumeroFactura = NumeroFactura;
        this.ValorFactura = ValorFactura;
        this.FechaVencimiento = FechaVencimiento;
        this.TipoFactura = TipoFactura;
    }
    
    public LocalDate getFechaAqusicion() {
        return FechaAqusicion;
    }

    public void setFechaAqusicion(LocalDate FechaAqusicion) {
        this.FechaAqusicion = FechaAqusicion;
    }

    public String getProveedor() {
        return Proveedor;
    }

    public void setProveedor(String Proveedor) {
        this.Proveedor = Proveedor;
    }

    public String getNumeroFactura() {
        return NumeroFactura;
    }

    

    public void setNumeroFactura(String NumeroFactura) {
        this.NumeroFactura = NumeroFactura;
    }

    public float getValorFactura() {
        return ValorFactura;
    }

    public void setValorFactura(float ValorFactura) {
        this.ValorFactura = ValorFactura;
    }

    public LocalDate getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate FechaVencimiento) {
        this.FechaVencimiento = FechaVencimiento;
    }

    public String getTipoFactura() {
        return TipoFactura;
    }

    public void setTipoFactura(String TipoFactura) {
        this.TipoFactura = TipoFactura;
    }
    
}
