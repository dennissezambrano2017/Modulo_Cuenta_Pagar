package DataView;

import Controller.Conexion;
import Model.Anticipo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author elect
 */
public class AnticipoDAO {

    // getAll trae todos los registro de la base de datos, de los anticipos
    // Consulta:
    // select "idAnticipo", importe, "fechaRegistro", descripcion, "idProveedor"
    //      from anticipo;
    public static List<Anticipo> getAll() {
        List<Anticipo> anticipos = new ArrayList<>();
        Conexion conn = new Conexion();
        String query = "select \"id_anticipo\", importe, \"fecha\", descripcion, \"id_proveedor\"\n"
                + "    from anticipo;";
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

    // El metodo getAllJson(), trae todos los anticipos con su proveedor,
    // toda esta información es traida en formato json, la cual se deserializa
    // con la biblioteca Gson de google.
    public static List<Anticipo> getAllJson() {
        String datos = null;
        Conexion conn = new Conexion();
        String query = "select select_all_anticipo_width_proveedor() as _anticipo;";
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
            Type collectionType = new TypeToken<List<Anticipo>>() {
            }.getType();
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
