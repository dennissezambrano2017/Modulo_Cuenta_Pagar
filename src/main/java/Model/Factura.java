/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.Date;
/**
 *
 * @author ninat
 */
public class Factura {
    private int id;
    private String nfactura;
    private String descripcion;
    private float importe;
    private float pagado;
    private Date fecha;
    private Date vencimiento;
    private int estado;
    private String nombre;
    private String ruc;
    private int idasiento;

    public Factura() {
    }
    

    public Factura(int id, String nfactura, String descripcion, float importe, float pagado, Date fecha, Date vencimiento, int estado, String ruc, String nombre) {
        this.id = id;
        this.nfactura = nfactura;
        this.descripcion = descripcion;
        this.importe = importe;
        this.pagado = pagado;
        this.fecha = fecha;
        this.vencimiento = vencimiento;
        this.estado = estado;
        this.ruc = ruc;
        this.nombre = nombre;
    }
    public Factura(int id, String nfactura, String descripcion, float importe, float pagado, Date fecha, Date vencimiento, int estado, String nombre) {
        this.id = id;
        this.nfactura = nfactura;
        this.descripcion = descripcion;
        this.importe = importe;
        this.pagado = pagado;
        this.fecha = fecha;
        this.vencimiento = vencimiento;
        this.estado = estado;
        this.nombre = nombre;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getPagado() {
        return pagado;
    }

    public void setPagado(float pagado) {
        this.pagado = pagado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public int getIdasiento() {
        return idasiento;
    }

    public void setIdasiento(int idasiento) {
        this.idasiento = idasiento;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    
    
}
