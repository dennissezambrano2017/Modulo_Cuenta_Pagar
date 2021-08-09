/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataView;

import Controller.Conexion;
import Model.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ebert
 */
public class ProveedorDAO extends Conexion {

     Connection con;
     Conexion conexion = new Conexion();
     private List<Proveedor> listaProveedor;
     private Proveedor proveedor;
     private ResultSet result;

     public ProveedorDAO() {
          conexion = new Conexion();
          listaProveedor = new ArrayList<>();
     }

     public List<Proveedor> llenar() {
          if (conexion.isEstado()) {
               try {
                    String sentencia = "SELECT * from proveedor p";
                    result = conexion.ejecutarConsulta(sentencia);
                    while (result.next()) {
                         listaProveedor.add(new Proveedor(result.getString("codigo"),
                                 result.getString("nombre"), result.getString("direccion"),
                                 result.getString("email"), result.getString("contacto"),
                                 result.getString("telefono"), result.getBoolean("estado")
                         ));
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

     public void InsertarProveedor(Proveedor proveedor) {

          try {
               this.Conectar();
               String sentencia = "INSERT INTO public.proveedor(\n"
                       + "	codigo, \"razonSocial\", ruc, nombre, direccion, email,\n"
                       + "	\"webPage\", contacto, telefono, estado, descuento,\n"
                       + "	\"diasNeto\", \"diasDescuento\", \"cantDiasVencidos\", descripcion)\n"
                       + "	VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
               PreparedStatement preparedStatement = this.getCnx().prepareStatement(sentencia);

               preparedStatement.setString(1, proveedor.getCodigo());
               preparedStatement.setString(2, proveedor.getRazonSocial());
               preparedStatement.setString(3, proveedor.getRuc());
               preparedStatement.setString(4, proveedor.getNombre());
               preparedStatement.setString(5, proveedor.getDireccion());
               preparedStatement.setString(6, proveedor.getEmail());
               preparedStatement.setString(7, proveedor.getWebPage());
               preparedStatement.setString(8, proveedor.getContacto());
               preparedStatement.setString(9, proveedor.getTelefono());
               preparedStatement.setBoolean(10, proveedor.isEstado());
               preparedStatement.setDouble(11, proveedor.getDescuento());

               preparedStatement.setInt(12, proveedor.getDiasNeto());

               preparedStatement.setInt(13, proveedor.getDiasDescuento());
               preparedStatement.setInt(14, proveedor.getCantDiasVencidos());

               preparedStatement.setString(15, proveedor.getDescripcion());
               preparedStatement.executeUpdate();
               preparedStatement.close();

          } catch (SQLException ex) {
               System.out.println(ex.getMessage() + " error en conectarse EBERT GUARANGA");
          } finally {
               this.cerrarConexion();
          }

     }

}
