/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataView;

import Controller.Conexion;
import Model.Condiciones;
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
     private Condiciones condiciones;

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

    

  

    public void insertar(Proveedor proveedor, Condiciones condiciones){
     
         String cadena = "INSERT INTO public.proveedor(\n" +
"	 codigo, razonsocial, ruc, nombre, direccion, email, webpage, contacto, telefono, estado)\n" +
"	VALUES ('"+proveedor.getCodigo()+"','"
                 +proveedor.getRazonSocial()+"','"+proveedor.getRuc()+"','"
                 +proveedor.getNombre()+"','"+proveedor.getDireccion()+"','"
                 +proveedor.getEmail()+"','"+proveedor.getWebPage()+"','"
                 +proveedor.getContacto()+"','"+proveedor.getTelefono()+"','"
                 +proveedor.isEstado()+"')";         
         System.out.print(cadena);
         conexion.Ejecutar2(cadena);
         
         String cadena2 = "INSERT INTO public.condiciones(\n" +
"	 descuento, \"diasNeto\", \"diasDescuento\", \"cantDiasVencidos\", descripcion, \"idProveedor\")\n" +
"	VALUES ( "+condiciones.getDescuento()+", "
                 + ""+condiciones.getDiasNeto()+", "+condiciones.getDiasDescuento()+","
                 + ""+condiciones.getCantDiasVencidos()+",'"+condiciones.getDescripcion()+"', (SELECT idproveedor FROM proveedor ORDER BY  idproveedor DESC LIMIT 1));\n" +
"	\n" +
"	";
           System.out.print(cadena2);
         conexion.Ejecutar2(cadena2);
    }
}
