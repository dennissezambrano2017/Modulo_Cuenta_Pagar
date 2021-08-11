/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Conexion;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elect
 * Modelo para trabajar con los anticipos
 */
public class Anticipo {
    
    private int id_anticipo;
    private Double importe;
    private LocalDate fechaRegistro;
    private String descripcion;
    private int id_proveedor;
    private Proveedor _Proveedor;

    public Anticipo() {
    }

    public int getId_anticipo() {
        return id_anticipo;
    }

    public void setId_anticipo(int id_anticipo) {
        this.id_anticipo = id_anticipo;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public Proveedor getProveedor() {
        return _Proveedor;
    }

    public void setProveedor(Proveedor _Proveedor) {
        this._Proveedor = _Proveedor;
    }
    
    // Metodo aux para comunicación con db
    public Anticipo GetDBProveedor() {
        if (this.id_proveedor == 0) {
            return null;
        }
        this._Proveedor = Proveedor.getOneProveedor(this.id_proveedor);
        return this;
    }
    
    // getAll trae todos los registro de la base de datos, de los anticipos
    // Consulta:
    // select "idAnticipo", importe, "fechaRegistro", descripcion, "idProveedor"
    //      from anticipo;
    public static List<Anticipo> getAll() {
        List<Anticipo> anticipos = new ArrayList<>();
        Conexion conn = new Conexion();
        String query =  "select \"idAnticipo\", importe, \"fechaRegistro\", descripcion, \"idProveedor\"\n" +
                        "    from anticipo;";
        try {
            conn.abrirConexion();
            Statement stmt = conn.conex.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Anticipo anticipo = new Anticipo();
                anticipo.setId_anticipo(rs.getInt("idAnticipo"));
                anticipo.setImporte(rs.getDouble("importe"));
                anticipo.setFechaRegistro(rs.getObject("fechaRegistro", LocalDate.class));
                anticipo.setDescripcion(rs.getString("descripcion"));
                anticipo.setId_proveedor(rs.getInt("idProveedor"));
                
                anticipos.add(anticipo);
            }
            conn.conex.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        
        return anticipos;
    }
    
    // GetAllDBProveedor va iterando por cada anticipo y va a traer el proveedor
    // de cada anticipo.
    public static List<Anticipo> GetAllDBProveedor(List<Anticipo> anticipos) {
        anticipos.forEach(ant -> {
            ant.GetDBProveedor();
        });
        return anticipos;
    }
}
