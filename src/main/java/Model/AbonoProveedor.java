/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author PAOLA
 */
public class AbonoProveedor {
    

    private int idAbonoProveedor;
    private String referencia;
    private int idAsiento;
    private int idTipoPago;
    private int idTipoBanco;
    private int idProveedor;
    private Date fecha;
    private float Pago;
    private String periodo;
    private String detalletipoPago;
    private String nombreProveedor;

    public AbonoProveedor() {
    }

    public AbonoProveedor(String referencia, int idProveedor, Date fecha, 
            float Pago, String periodo, String detalletipoPago, 
            String nombreProveedor) {
        this.referencia = referencia;
        this.idProveedor = idProveedor;
        this.fecha = fecha;
        this.Pago = Pago;
        this.periodo = periodo;
        this.detalletipoPago = detalletipoPago;
        this.nombreProveedor = nombreProveedor;
    }

    
    public AbonoProveedor(int idAbonoProveedor, String referencia,
            int idAsiento, int idTipoPago, int idTipoBanco,
            int idProveedor, Date fecha) {
        this.idAbonoProveedor = idAbonoProveedor;
        this.referencia = referencia;
        this.idAsiento = idAsiento;
        this.idTipoPago = idTipoPago;
        this.idTipoBanco = idTipoBanco;
        this.idProveedor = idProveedor;
        this.fecha = fecha;
    }

    public int getIdAbonoProveedor() {
        return idAbonoProveedor;
    }

    public void setIdAbonoProveedor(int idAbonoProveedor) {
        this.idAbonoProveedor = idAbonoProveedor;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public int getIdTipoBanco() {
        return idTipoBanco;
    }

    public void setIdTipoBanco(int idTipoBanco) {
        this.idTipoBanco = idTipoBanco;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getPago() {
        return Pago;
    }

    public void setPago(float Pago) {
        this.Pago = Pago;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getDetalletipoPago() {
        return detalletipoPago;
    }

    public void setDetalletipoPago(String detalletipoPago) {
        this.detalletipoPago = detalletipoPago;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

}
