
package Model;

import Controller.Conexion;
import java.sql.PreparedStatement;
import java.util.Date;

/**
 *
 * @author elect
 * Modelo para trabajar con los anticipos
 */
public class Anticipo {
    
    private String id_anticipo;
    private Double importe;
    private Date fecha;
    private String descripcion;
    private int id_proveedor;
    private Proveedor proveedor;

    public Anticipo() {
    }

    public String getId_anticipo() {
        return id_anticipo;
    }

    public void setId_anticipo(String id_anticipo) {
        this.id_anticipo = id_anticipo;
    }

    

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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

  
    
    // Metodo aux para comunicación con db
    public Anticipo GetDBProveedor() {
        if (this.id_proveedor == 0) {
            return null;
        }
        this.proveedor = Proveedor.getOneProveedor(this.id_proveedor);
        return this;
    }
    

 
    
    
    // el metodo InsertDB, inserta el objeto anticipo a la base de datos
    // mediante la funcion en postgres "insert_anticipo()" la cual se le pasa
    // los parametros.
    public void InsertDB() {
        System.out.println("Insertar objecto a la db");
        System.out.println(this.id_anticipo);
        System.out.println(this.fecha);
        System.out.println(this.importe);
        System.out.println(this.id_proveedor);
        
        Conexion conn = new Conexion();
        String query =  "select insert_anticipo(?, ?, ?, ?);";
        try {
            conn.abrirConexion();
            
            PreparedStatement stmt = conn.conex.prepareStatement(query);
            stmt.setInt(1, this.id_proveedor);
            stmt.setDouble(2, this.importe);
            stmt.setObject(3, new java.sql.Date(this.fecha.getTime()));
            stmt.setString(4, this.descripcion);
            
            stmt.execute();
            //ResultSet rs = stmt.executeQuery(query);
          
            conn.conex.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    // El metodo UpdateDB actualiza el objeto java, en la base de datos
    // mediante el id_anticipo.
    // Consulta
    //update anticipo set "importe"=33, "fechaRegistro"='10/05/2021', descripcion='sdffsdf', "idProveedor"=1
    //where "idAnticipo"=17;
    public void UpdateDB() {
        System.out.println("Update objecto a la db");
        System.out.println(this.id_anticipo);
        System.out.println(this.fecha);
        System.out.println(this.importe);
        System.out.println(this.id_proveedor);
        
        Conexion conn = new Conexion();
        String query =  "update anticipo set \"importe\"=?, \"fecha\"=?, descripcion=?, \"id_proveedor\"=?\n" +
                        "    where \"id_anticipo\"=?;";
        try {
            conn.abrirConexion();
            
            PreparedStatement stmt = conn.conex.prepareStatement(query);
            stmt.setDouble(1, this.importe);
            //stmt.setDate(2, (java.sql.Date) this.fecha);
            stmt.setObject(2, new java.sql.Date(this.fecha.getTime()));
            stmt.setString(3, this.descripcion);
            stmt.setInt(4, this.id_proveedor);
            stmt.setString(5, this.id_anticipo);
            
            stmt.execute();
            //ResultSet rs = stmt.executeQuery(query);
          
            conn.conex.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void deleteDB() {
        System.out.println("Delete objecto a la db");
        System.out.println(this.id_anticipo);
        System.out.println(this.fecha.toString());
        System.out.println(this.importe);
        System.out.println(this.id_proveedor);
        
        Conexion conn = new Conexion();
        String query =  "delete from anticipo where id_anticipo=?;";
        try {
            conn.abrirConexion();
            
            PreparedStatement stmt = conn.conex.prepareStatement(query);
            stmt.setString(1, this.getId_anticipo());
            
            stmt.execute();
            
            conn.conex.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
