/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataView;

import Controller.Conexion;
import Model.Proveedor;
import Model.Condiciones;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ebert
 */
public class CondicionesDAO implements Serializable {

     Conexion conexion = new Conexion();
     private Proveedor proveedor;

     public ArrayList<Condiciones> llenarCondiciones() throws Exception {
          ArrayList<Condiciones> lista = new ArrayList<Condiciones>();
          try {
               this.conexion.Conectar();

               String sentencia = "SELECT c.descuento,c.diasneto,c.diasdescuento,"
                       + "c.cantdiasvencidos,c.descripcion,"
                       + "p.idproveedor, p.codigo,p.razonsocial,p.ruc,p.nombre,"
                       + "p.direccion,p.email,p.webpage,p.contacto,"
                       + "p.telefono,p.estado FROM condiciones c "
                       + "INNER JOIN proveedor p ON p.idproveedor = c.idproveedor order by p.idproveedor";
               PreparedStatement prs = conexion.getCnx().prepareStatement(sentencia);
               ResultSet result = prs.executeQuery();
               while (result.next()) {
                    Proveedor p = new Proveedor();
                    Condiciones c = new Condiciones();
                    c.setDescuento(result.getDouble("descuento"));
                    c.setDiasNeto(result.getInt("diasneto"));
                    c.setDiasDescuento(result.getInt("diasdescuento"));
                    c.setCantDiasVencidos(result.getInt("cantdiasvencidos"));
                    c.setDescripcion(result.getString("descripcion"));
                    p.setIdProveedor(result.getInt("idproveedor"));
                    p.setCodigo(result.getString("codigo"));
                    p.setRazonSocial(result.getString("razonsocial"));
                    p.setRuc(result.getString("ruc"));
                    p.setNombre(result.getString("nombre"));
                    p.setDireccion(result.getString("direccion"));
                    p.setEmail(result.getString("email"));
                    p.setWebPage(result.getString("webpage"));
                    p.setContacto(result.getString("contacto"));
                    p.setTelefono(result.getString("telefono"));
                    p.setEstado(result.getBoolean("estado"));
                    c.setProveedor(p);
                    lista.add(c);
                    System.out.println("Sentencia correcta condicionesDAO");

               }
          } catch (Exception e) {
               throw e;

          } finally {
               this.conexion.cerrarConexion();
          }
          return lista;
     }

     public void insertarCondiciones(Condiciones c) throws Exception {
          try {
               String sentencia = "INSERT INTO public.condiciones(descuento,"
                       + " diasneto, diasdescuento, cantdiasvencidos,"
                       + " descripcion, idproveedor)\n"
                       + "	VALUES (?,?,?,?,?,"
                       + "(SELECT idproveedor FROM proveedor ORDER BY idproveedor DESC LIMIT 1));";
               this.conexion.Conectar();
               PreparedStatement pst = this.conexion.conex.prepareStatement(sentencia);
               pst.setDouble(1, c.getDescuento());
               pst.setInt(2, c.getDiasNeto());
               pst.setInt(3, c.getDiasDescuento());
               pst.setInt(4, c.getCantDiasVencidos());
               pst.setString(5, c.getDescripcion());
               pst.executeUpdate();
               conexion.ejecutar(sentencia);
               System.out.println(sentencia);

          } catch (Exception e) {
               throw e;

          } finally {
               this.conexion.cerrarConexion();
          }

     }

     public void updateCondiciones(Condiciones c) throws Exception {
          Proveedor proveedor = new Proveedor();
          try {
               String sentencia = "UPDATE public.condiciones\n"
                       + "	SET descuento=?, diasneto=?, diasdescuento=?, cantdiasvencidos=?, descripcion=?\n"
                       + "	WHERE idproveedor =?;";
               this.conexion.Conectar();

               System.out.println("´´´´´´´´´´´´´´´´´´´´´´´´´´jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjkkkkkkkk");
               System.out.println(sentencia);
               PreparedStatement pst = this.conexion.conex.prepareStatement(sentencia);
               pst.setDouble(1, c.getDescuento());
               System.out.println(c.getDescuento());
               pst.setInt(2, c.getDiasNeto());
               System.out.println(c.getDiasNeto());
               pst.setInt(3, c.getDiasDescuento());
               pst.setInt(4, c.getCantDiasVencidos());
               System.out.println(c.getDescripcion());
               pst.setString(5, c.getDescripcion());
               System.out.println(proveedor.getIdProveedor() + "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
               System.out.println("´´´´´´´´´´´´´´´´´´´´´´´´´´jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjkkkkkkkk");
               pst.setInt(6, c.getProveedor().getIdProveedor());
               System.out.println(c.getProveedor().getIdProveedor());
               pst.executeUpdate();
               System.out.println("saliendo de update condicionesdao");
               conexion.ejecutar(sentencia);
               System.out.println(sentencia);

          } catch (Exception e) {
               throw e;

          } finally {
               this.conexion.cerrarConexion();
          }

     }

}
