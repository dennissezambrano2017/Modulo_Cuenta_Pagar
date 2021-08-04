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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ebert
 */
public class ProveedorDAO {
     Connection con;
     Conexion conex;
     private Proveedor proveedor;
     private ResultSet result;
     private List<Proveedor> listaProveedors;

     public ProveedorDAO() {
          conex = new Conexion();
          listaProveedors = new ArrayList<>();
     }

     public ProveedorDAO(Proveedor proveedor) {
          conex = new Conexion();
          this.proveedor = proveedor;
     }

     public List<Proveedor> llenar() {
          if (conex.isEstado()) {
               try {
                    String sentencia = "select * from public.\"proveedor\"";
                    result = conex.ejecutarConsulta(sentencia);
                    while (result.next()) {
                         listaProveedors.add(new Proveedor(
                                 result.getString("codigo"),
                                 result.getString("nombre"),
                                 result.getString("email"),
                                 result.getString("telefono"),
                                 result.getString("contacto"),
                                 result.getBoolean("estado")));
                    }
                    result.close();
                    return listaProveedors;
               } catch (SQLException ex) {
                    System.out.println(ex.getMessage() + " error en conectarse EBERT");
               } finally {
                    conex.cerrarConexion();
               }
          }
          return listaProveedors;
     }

     public void Insertar( Proveedor proveedor) {
          if (conex.isEstado()) {
               try {
                    String sentencia = "";
                    PreparedStatement preparedStatement =con.prepareStatement(sentencia);
                    preparedStatement.setString(1,proveedor.getCodigo());
                    preparedStatement.setString(2,proveedor.getRazonSocial());
                    preparedStatement.setString(3,proveedor.getRuc());
                    preparedStatement.setString(4,proveedor.getNombre());
                    preparedStatement.setString(5,proveedor.getDireccion());
                    preparedStatement.setString(6,proveedor.getEmail());
                    preparedStatement.setString(7,proveedor.getWebPage());
                    preparedStatement.setString(8,proveedor.getContacto());
                    preparedStatement.setString(9,proveedor.getTelefono());
                    preparedStatement.setBoolean(10,proveedor.isEstado());
                     preparedStatement.setDouble(11, proveedor.getDescuento());
                     
                    preparedStatement.setInt(12, proveedor.getDiasNeto());
                    
                    preparedStatement.setInt(13, proveedor.getDiasDescuento());
                    preparedStatement.setInt(14, proveedor.getCantDiasVencidos());
                    
                    preparedStatement.setString(15,proveedor.getDescripcion());
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    
                    
                   
               } catch (SQLException ex) {
                    System.out.println(ex.getMessage() + " error en conectarse EBERT GUARANGA");
               } finally {
                    conex.cerrarConexion();
               }
          }
     }

}
