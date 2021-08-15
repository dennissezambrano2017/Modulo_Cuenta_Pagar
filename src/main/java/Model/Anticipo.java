
package Model;

import Controller.Conexion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    
    // getAll trae todos los registro de la base de datos, de los anticipos
    // Consulta:
    // select "idAnticipo", importe, "fechaRegistro", descripcion, "idProveedor"
    //      from anticipo;
    public static List<Anticipo> getAll() {
        List<Anticipo> anticipos = new ArrayList<>();
        Conexion conn = new Conexion();
        String query =  "select \"id_anticipo\", importe, \"fecha\", descripcion, \"id_proveedor\"\n" +
                        "    from anticipo;";
        try {
            conn.abrirConexion();
            Statement stmt = conn.conex.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Anticipo anticipo = new Anticipo();
                anticipo.setId_anticipo(rs.getString("id_anticipo"));
                anticipo.setImporte(rs.getDouble("importe"));
                anticipo.setFecha(rs.getObject("fecha", Date.class));
                anticipo.setDescripcion(rs.getString("descripcion"));
                anticipo.setId_proveedor(rs.getInt("id_proveedor"));
                
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
        anticipos.forEach((Anticipo ant) -> {
            ant.GetDBProveedor();
        });
        return anticipos;
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
    
    // El metodo getAllJson(), trae todos los anticipos con su proveedor,
    // toda esta información es traida en formato json, la cual se deserializa
    // con la biblioteca Gson de google.
    public static List<Anticipo> getAllJson() {
        String datos = null;
        Conexion conn = new Conexion();
        String query =  "select select_all_anticipo_width_proveedor() as _anticipo;";
        try {
            conn.abrirConexion();
            
            Statement statement = conn.conex.createStatement();
            //PreparedStatement stmt = conn.conex.prepareStatement(query);
            
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) { 
                datos = rs.getString("_anticipo");
            }
            
            conn.conex.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        // Contenedor de los datos.
        List<Anticipo> anticiposDB = null;
        
        try {
            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<Anticipo>>(){}.getType();
            // Deserializamos los datos de json a java objeto.
            anticiposDB = gson.fromJson(datos, collectionType);
            
            System.out.println("Llegaron los datos correctamente.");
            System.out.println(datos);
            
        } catch (Exception ex) {
            System.out.println("Error gson: " + ex.getMessage());
        }
        
        
        return anticiposDB;
    }
}
