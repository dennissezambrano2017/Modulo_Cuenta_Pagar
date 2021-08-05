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

/**
 *
 * @author ebert
 */
public class ProveedorDAO extends Conexion {

     Connection con;
     private Proveedor proveedor;
     private ResultSet result;

     public ArrayList<Proveedor> llenar() {
          ArrayList<Proveedor> lista = new ArrayList<>();

          try {
               String sentencia = "select * from public.\"proveedor\"";
               this.Conectar();
               PreparedStatement pst = this.getCnx().prepareStatement(sentencia);
               result = pst.executeQuery();
               while (result.next()) {
                    Proveedor p = new Proveedor();
                    p.setCodigo(result.getString("codigo"));
                    p.setNombre(result.getString("nombre"));
                    p.setEmail(result.getString("email"));
                    p.setTelefono(result.getString("telefono"));
                    p.setContacto(result.getString("contacto"));
                    p.setEstado(result.getBoolean("estado"));
                    p.setEstado(result.getBoolean("estado"));
                    p.setRuc(result.getString("ruc"));
                    p.setWebPage(result.getString("webpage"));
                    p.setDireccion(result.getString("direccion"));
                    p.setRazonSocial(result.getString("razonSocial"));

                    p.setCantDiasVencidos(result.getInt("cantDiasVencidos"));
                    p.setDescuento(result.getDouble("descuento"));
                    p.setDiasNeto(result.getInt("diasNeto"));
                    p.setDiasDescuento(result.getInt("diasDescuento"));
                    p.setDescripcion(result.getString("descripcion"));

                    lista.add(p);
               }
          } catch (SQLException ex) {
               System.out.println(ex.getMessage() + " error en conectarse EBERT");
          } finally {
               this.cerrarConexion();
          }

          return lista;
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

     public void EditarProveedor(Proveedor proveedor) {

          try {
               this.Conectar();
               String sentencia = "UPDATE public.proveedor\n"
                       + "	SET  codigo=?, \"razonSocial\"=?, ruc=?, nombre=?, direccion=?, email=?, \"webPage\"=?, contacto=?, telefono=?, estado=?, descuento=?, \"diasNeto\"=?, \"diasDescuento\"=?, \"cantDiasVencidos\"=?, descripcion=?\n"
                       + "	WHERE \"idProveedor\" = ?;";
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
               preparedStatement.setInt(16, proveedor.getIdProveedor());
               preparedStatement.executeUpdate();

          } catch (SQLException ex) {
               System.out.println(ex.getMessage() + " error en conectarse EBERT GUARANGA");
          } finally {
               this.cerrarConexion();
          }

     }

}
