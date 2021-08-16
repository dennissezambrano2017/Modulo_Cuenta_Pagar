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
                 System.out.println("Sentencia ingrsando 1 condicionesDAO");
               String sentencia = "SELECT c.diasneto,c.diasdescuento,c.cantdiasvencidos,c.descripcion,p.codigo,p.razonsocial,p.ruc,p.nombre,p.direccion,p.email,p.webpage,p.contacto,p.telefono,p.estado FROM condiciones c INNER JOIN proveedor p ON p.idproveedor = c.idproveedor";
               PreparedStatement prs = conexion.getCnx().prepareStatement(sentencia);
               ResultSet result = prs.executeQuery();
                 System.out.println("Sentencia ingresando 2 condicionesDAO");
               while (result.next()) {
                      System.out.println("Sentencia ingresando 3 condicionesDAO");
                    Proveedor p = new Proveedor();
                    Condiciones c = new Condiciones();
                     System.out.println("Sentencia ingresando 3.1 condicionesDAO");
                    c.setDiasNeto(result.getInt("diasneto"));
                    
                     System.out.println("Sentencia ingresando 3.2 condicionesDAO");
                    c.setDiasDescuento(result.getInt("diasdescuento"));
                    
                     System.out.println("Sentencia ingresando 3.3 condicionesDAO");
                    c.setCantDiasVencidos(result.getInt("cantdiasvencidos"));
                    
                     System.out.println("Sentencia ingresando 3.4 condicionesDAO");
                    c.setDescripcion(result.getString("descripcion"));
                       System.out.println("Sentencia ingresando 4 condicionesDAO");
                    p.setCodigo(result.getString("codigo"));
                     System.out.println("Sentencia ingresando 4.1 condicionesDAO");
                    p.setRazonSocial(result.getString("razonsocial"));
                     System.out.println("Sentencia ingresando 4.2 condicionesDAO");
                    p.setRuc(result.getString("ruc"));
                     System.out.println("Sentencia ingresando 4.3 condicionesDAO");
                    p.setNombre(result.getString("nombre"));
                     System.out.println("Sentencia ingresando 4.4 condicionesDAO");
                    p.setDireccion(result.getString("direccion"));
                     System.out.println("Sentencia ingresando 4.5 condicionesDAO");
                    p.setEmail(result.getString("email"));
                     System.out.println("Sentencia ingresando 4.6 condicionesDAO");
                    p.setWebPage(result.getString("webpage"));  
                     System.out.println("Sentencia ingresando 4.7 condicionesDAO");
                    p.setContacto(result.getString("contacto"));
                     System.out.println("Sentencia ingresando 4.8 condicionesDAO");
                    p.setTelefono(result.getString("telefono"));
                     System.out.println("Sentencia ingresando 4.9 condicionesDAO");
                    p.setEstado(result.getBoolean("estado"));
                       System.out.println("Sentencia ingresando 5 condicionesDAO");
                    c.setProveedor(p);
                       System.out.println("Sentencia ingresando 6 condicionesDAO");
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
}
