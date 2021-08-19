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

     public Proveedor getProveedor() {
          return proveedor;
     }

     public void setProveedor(Proveedor proveedor) {
          this.proveedor = proveedor;
     }

     public ProveedorDAO() {
          conexion = new Conexion();
          listaProveedor = new ArrayList<>();
          condiciones = new Condiciones();
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

     public void update(Proveedor proveedor, String codigo) throws SQLException {
         
          try {
                 this.conexion.Conectar();
                  System.out.println("EDITAR METODOPROVEEDOR----------------------------------------------------------------");
               String cadena = "UPDATE public.proveedor\n"
                       + "	SET  razonsocial= '"+proveedor.getRazonSocial()+"', ruc='"+proveedor.getRuc()+"', "
                       + "nombre='"+proveedor.getNombre()+"', direccion='"+proveedor.getDireccion()+"', "
                       + "email='"+proveedor.getEmail()+"', webpage='"+proveedor.getWebPage()+"', "
                       + "contacto='"+proveedor.getContacto()+"', telefono='"+proveedor.getTelefono()+"',"
                       + " estado='"+proveedor.isEstado()+"'\n"
                       + "	WHERE codigo ='"+codigo+"';";
             
              
               conexion.ejecutar(cadena);
              
          } catch (Exception e) {
               throw e;
          } finally {
               this.conexion.cerrarConexion();
          }

     }
     
     
     
     public void updateCondicionesProve(Condiciones c, int cod) throws Exception {
          Proveedor proveedor = new Proveedor();
          System.out.println(c.getProveedor().getIdProveedor());
          System.out.println(proveedor.getIdProveedor());
          System.out.println(this.proveedor.getIdProveedor());
          
          System.out.println(cod);
          System.err.println("_________________________________________________________________________________________");
          try {
               conexion.Conectar();
               String sentencia = "UPDATE public.condiciones\n" +
"	SET descuento='"+c.getDescuento()+"', diasneto='"+c.getDiasNeto()+"',"
                       + " diasdescuento='"+c.getDiasDescuento()+"', cantdiasvencidos='"+c.getCantDiasVencidos()+"',"
                       + " descripcion='"+c.getDescripcion()+"'\n" +
"	WHERE idproveedor = '"+cod+"';";
               this.conexion.Conectar();
               
               System.out.println("´´´´´´´´´´´´´´´´´´´´´´´´´´jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjkkkkkkkk");
               System.out.println(sentencia);
      
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
