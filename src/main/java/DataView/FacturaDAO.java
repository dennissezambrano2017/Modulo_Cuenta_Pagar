/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataView;

import Controller.Conexion;
import Model.Factura;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ninat
 */
public class FacturaDAO {

    Conexion conexion = new Conexion();
    private Factura factura;
    private ResultSet result;
    private List<Factura> listaFacturas;

    public FacturaDAO() {
        conexion = new Conexion();
        listaFacturas = new ArrayList<>();
    }

    public FacturaDAO(Factura factura) {
        conexion = new Conexion();
        this.factura = factura;
    }

    public List<Factura> llenar() {
        if (conexion.isEstado()) {
            try {
                String sentencia = "SELECT f.idfactura,f.nfactura,f.descripcion,"
                        + "f.importe,(f.importe - f.pagado) as pendiente,f.fecha,"
                        + "f.vencimiento,f.estado, p.nombre from factura as f "
                        + "INNER JOIN proveedor as p on (f.idproveedor = p.idproveedor)";
                result = conexion.ejecutarConsulta(sentencia);
                System.out.println("Factura: "+result.toString());
                while (result.next()) {
                    listaFacturas.add(new Factura(result.getInt("idfactura"),
                            result.getString("nfactura"), result.getString("descripcion"),
                            result.getFloat("importe"), result.getFloat("pendiente"),
                            result.getObject("fecha",LocalDate.class), 
                            result.getObject("vencimiento",LocalDate.class),
                            result.getInt("estado"), result.getString("nombre")));
                }
                result.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }
        return listaFacturas;
    }
    
//    public List<Factura> llenarEdit(String nfactura) {
//        if (conexion.isEstado()) {
//            try {
//                String sentencia = "SELECT f.nfactura,f.descripcion," 
//                        +"f.importe,f.pagado,f.fecha,f.vencimiento,p.nombre " 
//                        +"from factura as f INNER JOIN proveedor as p on "
//                        + "(f.idproveedor = p.idproveedor) and f.nfactura ='"+nfactura+"'";
//                result = conexion.ejecutarConsulta(sentencia);
//                System.out.println("Factura: "+result.toString());
//                while (result.next()) {
//                    listaFacturas.add(new Factura(
//                            result.getString("nfactura"), result.getString("descripcion"),
//                            result.getFloat("importe"), result.getFloat("pagado"),
//                            result.getObject("fecha",LocalDate.class), 
//                            result.getObject("vencimiento",LocalDate.class),
//                            result.getString("nombre")));
//                    
//                }
//                System.out.print("ESTOY LLENANDO: "+sentencia);
//                System.out.print(listaFacturas.size());
//                result.close();
//            } catch (SQLException ex) {
//                System.out.println(ex.getMessage() + " error en conectarse");
//            } finally {
//                conexion.cerrarConexion();
//            }
//        }
//        return listaFacturas;
//    }

    public void Insertar(Factura factura) {
        String cadena = "INSERT INTO public.factura("
                + "nfactura, descripcion, importe, pagado, fecha, vencimiento,idproveedor)"
                + " VALUES ('" + factura.getNfactura() + "','"
                + factura.getDescripcion() + "'," + factura.getImporte() + ","
                + factura.getPagado() + ",'" + factura.getFecha() + "','"
                + factura.getVencimiento() + "',(Select idproveedor from proveedor p "
                + " where p.ruc = '" + factura.getRuc()+ "'))";
        System.out.print(cadena);
        conexion.Ejecutar2(cadena);
    }
    
    public void Actualizar(Factura factura) {
        String cadena = "update factura set " +
                "descripcion = '"+ factura.getDescripcion() + 
                "', importe = " + factura.getImporte() + 
                " , pagado = " + factura.getPagado() +
                " , fecha = '" + factura.getFecha() + 
                "' ,vencimiento = '"+ factura.getVencimiento() +
                "', idproveedor = (Select idproveedor from proveedor " +
                "p where p.ruc = '" + factura.getRuc()+ "') "+
                "where nfactura = '" + factura.getNfactura() + "'";
        System.out.print(cadena);
        conexion.Ejecutar2(cadena);
    }

    //Consultas
    
    public float buscarPagado(String nfactura){
        float pagado = 0;
         if (conexion.isEstado()) {
            try {
                String sentencia = "select pagado from factura where nfactura= '"
                        + nfactura + "'";
                result = conexion.ejecutarConsulta(sentencia);
                while (result.next()) {
                    pagado = result.getFloat("pagado");
                }
                result.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }
        return pagado;
    }
}
