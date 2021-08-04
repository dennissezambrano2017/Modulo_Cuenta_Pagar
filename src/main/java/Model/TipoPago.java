/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author PAOLA
 */
public class TipoPago {
    private int idTipoPago;
    private String descripcion;

    public TipoPago() {
    }

    public TipoPago(int idTipoPago, String descripcion) {
        this.idTipoPago = idTipoPago;
        this.descripcion = descripcion;
    }

    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
