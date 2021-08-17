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
    private boolean canEdit;
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
        canEdit=false;
    }

    public DetalleAbono(float pago, String periodo) {
        this.pago = pago;
        this.periodo = periodo;
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

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }
    
     public String getSentencia(String nfactura)
    {
         String sentencia = String.format("INSERT INTO public.detalleabono(\n" +
"	idabonoproveedor, pago, periodo, idfactura)\n" +
"	VALUES ((select max(idabonoproveedor)from abonoproveedor), '%1$s','%2$s',(select f.idfactura from factura f\n" +
"       where f.nfactura=%3$d));",getPago(),getPeriodo(),nfactura); 
        System.out.print(sentencia);
        return sentencia;
    }

}
