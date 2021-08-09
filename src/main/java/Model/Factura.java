/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Controller.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private LocalDate fecha;
    private LocalDate vencimiento;
    private int estado;
    private int idproveedor;
    private Proveedor proveedor;
    private String nombre;
    private String ruc;
    private int idasiento;
    private float pendiente;
//double precision
    public Factura() {
    }

    //Paola: Usa este Constructor
    public Factura(String nfactura, float importe, float pagado, LocalDate fecha, LocalDate vencimiento, float pendiente) {
        this.nfactura = nfactura;
        this.importe = importe;
        this.pagado = pagado;
        this.fecha = fecha;
        this.vencimiento = vencimiento;
        this.pendiente = pendiente;
    }
    
    
    //Diana Constructor para Buscar proveedor e insertar 
    public Factura(int id, String nfactura, String descripcion, float importe, float pendiente, LocalDate fecha, LocalDate vencimiento, int estado, String ruc, String nombre) {
        this.id = id;
        this.nfactura = nfactura;
        this.descripcion = descripcion;
        this.importe = importe;
        this.pendiente = pendiente;
        this.fecha = fecha;
        this.vencimiento = vencimiento;
        this.estado = estado;
        this.ruc = ruc;
        this.nombre = nombre;
    }
    
    //Diana: Constructor para mostrar
    public Factura(int id, String nfactura, String descripcion, float importe, float pagado, LocalDate fecha, LocalDate vencimiento, int estado, String nombre) {
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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

    public float getPendiente() {
        return pendiente;
    }

    public void setPendiente(float pendiente) {
        this.pendiente = pendiente;
    }
    
    
    // metodos aux para comunicación con la db
    public static Factura getOneFactura(int id) {
        Factura fac = new Factura();
        
        Conexion conn = new Conexion();
        String query = "select idfactura, nfactura, descripcion, importe, pagado, fecha, vencimiento, estado, idproveedor, idasiento\n" +
                            "from factura\n" +
                            "where \"idfactura\"=?;";
        try {
            conn.abrirConexion();
            
            //Statement stmt = conn.conex.createStatement();
            PreparedStatement stmt = conn.conex.prepareStatement(query);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                fac.setId(rs.getInt("idfactura"));
                fac.setNfactura(rs.getString("nfactura"));
                fac.setDescripcion(rs.getString("descripcion"));
                fac.setImporte(rs.getFloat("importe"));
                fac.setPagado(rs.getFloat("pagado"));
                fac.setFecha(rs.getObject("fecha", LocalDate.class));
                fac.setVencimiento(rs.getObject("vencimiento", LocalDate.class));
                fac.setEstado(rs.getInt("estado"));
                fac.setIdproveedor(rs.getInt("idproveedor"));
                fac.setIdasiento(rs.getInt("idasiento"));
            }
            conn.conex.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

        return fac;
    }
    
    public Factura GetdbProveedor() {
        this.setProveedor(Proveedor.getOneProveedor(this.idproveedor));
        return this;
    }
}
