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
     private CondicionesDAO condicionesDAO;

     public ProveedorDAO() {
          conexion = new Conexion();
          listaProveedor = new ArrayList<>();
          condiciones = new Condiciones();
     }

     public List<Proveedor> llenar() {
          if (conexion.isEstado()) {
               try {
                    String sentencia = "SELECT * FROM condiciones INNER JOIN proveedor ON proveedor.idproveedor = condiciones.idproveedor";
                    result = conexion.ejecutarConsulta(sentencia);
                    while (result.next()) {
                         listaProveedor.add(new Proveedor(
                                 result.getString("codigo"),
                                 result.getString("nombre"),
                                 result.getString("contacto"),
                                 result.getString("direccion"),
                                 result.getString("email"),
                                 result.getString("telefono"),
                                 result.getBoolean("estado")
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

     public void insertarProveedor(Proveedor proveedor) throws Exception {
  System.out.println("DataView.CondicionesDAO.insertarCondiciones()....ENTRA n  INSERTAR .....");
          String cadena = "INSERT INTO public.proveedor(\n"
                  + "	 codigo, razonsocial, ruc, nombre, direccion, email, webpage, contacto, telefono, estado)\n"
                  + "	VALUES ('" + proveedor.getCodigo() + "','"
                  + proveedor.getRazonSocial() + "','" + proveedor.getRuc() + "','"
                  + proveedor.getNombre() + "','" + proveedor.getDireccion() + "','"
                  + proveedor.getEmail() + "','" + proveedor.getWebPage() + "','"
                  + proveedor.getContacto() + "','" + proveedor.getTelefono() + "','"
                  + proveedor.isEstado() + "')";
          System.out.print(cadena);
          conexion.Ejecutar2(cadena); 
                  
                       System.out.print("termina metodo DAO insertar coniciones");

     }

     public void update(Proveedor proveedor, Condiciones condiciones) {
          String cadena = "UPDATE public.proveedor\n"
                  + "	SET razonsocial='" + proveedor.getRazonSocial() + "',"
                  + " ruc='" + proveedor.getRuc() + "',"
                  + " nombre='" + proveedor.getNombre() + "',"
                  + " direccion='" + proveedor.getDireccion() + "',"
                  + " email='" + proveedor.getEmail() + "',"
                  + " webpage='" + proveedor.getWebPage() + "',"
                  + " contacto='" + proveedor.getContacto() + "',"
                  + " telefono='" + proveedor.getTelefono() + "',"
                  + " estado='" + proveedor.isEstado() + "'\n"
                  + "	WHERE idproveedor = '" + proveedor.getIdProveedor() + "' ;";
          System.out.print(cadena);
          conexion.Ejecutar2(cadena);
     }
}
