/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataView;

import Controller.Conexion;
import java.sql.Connection;
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
        factura = new Factura();
        conexion = new Conexion();
        listaFacturas = new ArrayList<>();
    }

    public FacturaDAO(Factura factura) {
        conexion = new Conexion();
        this.factura = factura;
    }

    public List<Factura> llenarP(String n) {
        if (conexion.isEstado()) {
            try {
                String sentencia = "SELECT f.idfactura,f.nfactura,f.descripcion,"
                        + "f.importe,f.pagado ,(f.importe - f.pagado) as pendiente,f.fecha,"
                        + "f.vencimiento,f.estado, p.nombre, f.habilitar from factura as f "
                        + "INNER JOIN proveedor as p on (f.idproveedor = p.idproveedor) "
                        + "where (f.importe - f.pagado) != 0 and habilitar = " + n;
                result = conexion.ejecutarConsulta(sentencia);
                System.out.println("Factura: " + result.toString());
                while (result.next()) {
                    listaFacturas.add(new Factura(result.getInt("idfactura"),
                            result.getString("nfactura"), result.getString("descripcion"),
                            result.getFloat("importe"), result.getFloat("pagado"),
                            result.getFloat("pendiente"),
                            result.getObject("fecha", LocalDate.class),
                            result.getObject("vencimiento", LocalDate.class),
                            result.getInt("estado"), result.getString("nombre"),
                            result.getInt("habilitar")));
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }
        return listaFacturas;
    }

    public List<Factura> llenar() {
        if (conexion.isEstado()) {
            try {
                String sentencia = "SELECT f.idfactura,f.nfactura,f.descripcion,"
                        + "f.importe,f.pagado ,(f.importe - f.pagado) as pendiente,f.fecha,"
                        + "f.vencimiento,f.estado, p.nombre, f.habilitar from factura as f "
                        + "INNER JOIN proveedor as p on (f.idproveedor = p.idproveedor) "
                        + "where (f.importe - f.pagado) != 0 and habilitar = 1";
                result = conexion.ejecutarConsulta(sentencia);
                System.out.println("Factura: " + result.toString());
                while (result.next()) {
                    listaFacturas.add(new Factura(result.getInt("idfactura"),
                            result.getString("nfactura"), result.getString("descripcion"),
                            result.getFloat("importe"), result.getFloat("pagado"),
                            result.getFloat("pendiente"),
                            result.getObject("fecha", LocalDate.class),
                            result.getObject("vencimiento", LocalDate.class),
                            result.getInt("estado"), result.getString("nombre"),
                            result.getInt("habilitar")));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }
        return listaFacturas;
    }

    public void Insertar(Factura factura) {
        if (conexion.isEstado()) {
            try {
                String cadena = "INSERT INTO public.factura("
                        + "nfactura, descripcion, importe, pagado, fecha, vencimiento,idproveedor,habilitar)"
                        + " VALUES ('" + factura.getNfactura() + "','"
                        + factura.getDescripcion() + "'," + factura.getImporte() + ","
                        + factura.getPagado() + ",'" + factura.getFecha() + "','"
                        + factura.getVencimiento() + "',(Select idproveedor from proveedor p "
                        + " where p.ruc = '" + factura.getRuc() + "'), 1)";
                System.out.print(cadena);
                conexion.Ejecutar2(cadena);
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }
    }

//
    public void Actualizar(Factura factura) {
        if (conexion.isEstado()) {
            try {
                String cadena = "select actualizarfactura('" + factura.getNfactura()
                        + "','" + factura.getDescripcion() + "'," + factura.getImporte()
                        + "," + factura.getPagado()
                        + ",'" + factura.getFecha()
                        + "','" + factura.getVencimiento()
                        + "','" + factura.getRuc() + "')";
                System.out.print(cadena);
                conexion.Ejecutar2(cadena);
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }
    }

    public void habilitar(List<Factura> selectedFactura) {

        System.out.print("HOLA ESTOY EN MANAGE HABILITAR");
        for (int i = 0; i < selectedFactura.size(); i++) {

            System.out.println(selectedFactura.get(i).getNfactura());
        }
    }

    public void dhabilitar(String n) {
        System.out.print("HOLA ESTOY EN MANAGE HABILITAR UNO");
        if (conexion.isEstado()) {
            try {
                String cadena = "select habilitarfactura(0,'" + n + "')";
                System.out.print(cadena);
                conexion.Ejecutar2(cadena);
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }
    }

    //Consultas
    public float buscarPagado(String nfactura) {
        float pagado = 0;
        if (conexion.isEstado()) {
            try {
                String sentencia = "select pagado from factura where nfactura= '"
                        + nfactura + "'";
                result = conexion.ejecutarConsulta(sentencia);
                while (result.next()) {
                    pagado = result.getFloat("pagado");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }
        return pagado;
    }
    
//Paola: Funcion para autorizar Pago
    public void AutorizarPago(String sentencia) {
        result= conexion.ejecutarConsulta(sentencia);
    }
//    public void AutorizarPago(String sentencia) {
//        result= conexion.ejecutarConsulta(sentencia);
//        System.out.println(result.getRow());
//    }
    
    
//    public int Autorizar(String sentencia) {
//        try {
//            connection = conexion.getCnx();
//            statement = connection.createStatement();
//            statement.executeUpdate(sentencia);
//            System.out.print("Si inserto");
//            return 1;
//        } catch (SQLException err) {
//            System.out.println(err + " Error de insertar");
//            return 0;
//        }
//    }
}
