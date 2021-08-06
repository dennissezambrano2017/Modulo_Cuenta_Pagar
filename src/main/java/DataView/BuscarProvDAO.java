/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataView;

import Controller.Conexion;
import Model.Proveedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ninat
 */
public class BuscarProvDAO {
    Conexion conexion = new Conexion();
    private Proveedor proveedor;
    private ResultSet result;
    private List<Proveedor> listaProveedor;

    public BuscarProvDAO() {
        conexion = new Conexion();
        listaProveedor = new ArrayList<>();
    }

    public BuscarProvDAO(Proveedor proveedor) {
        conexion = new Conexion();
        this.proveedor = proveedor;
    }
    
     public List<Proveedor> llenar() {
        if (conexion.isEstado()) {
            try {
                String sentencia = "SELECT * from proveedor";
                result = conexion.ejecutarConsulta(sentencia);
                while (result.next()) {
                    listaProveedor.add(new Proveedor(result.getString("codigo"),
                            result.getString("ruc"), result.getString("nombre")));
                }
                result.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }
        return listaProveedor;
    }

}