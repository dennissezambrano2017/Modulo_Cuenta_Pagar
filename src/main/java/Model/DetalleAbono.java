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
public class DetalleAbono {

    private int idDetalleAbono;
    private float pago;
    private int idAbonoProveedor;
    private String periodo;
    private int idFactura;

    public DetalleAbono() {
    }

    public DetalleAbono(int idDetalleAbono, float pago, int idAbonoProveedor, String periodo, int idFactura) {
        this.idDetalleAbono = idDetalleAbono;
        this.pago = pago;
        this.idAbonoProveedor = idAbonoProveedor;
        this.periodo = periodo;
        this.idFactura = idFactura;
    }

    public int getIdDetalleAbono() {
        return idDetalleAbono;
    }

    public void setIdDetalleAbono(int idDetalleAbono) {
        this.idDetalleAbono = idDetalleAbono;
    }

    public float getPago() {
        return pago;
    }

    public void setPago(float pago) {
        this.pago = pago;
    }

    public int getIdAbonoProveedor() {
        return idAbonoProveedor;
    }

    public void setIdAbonoProveedor(int idAbonoProveedor) {
        this.idAbonoProveedor = idAbonoProveedor;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

}
