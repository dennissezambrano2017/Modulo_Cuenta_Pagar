/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataView;

import Controller.Conexion;
import Model.AbonoProveedor;
import Model.DetalleAbono;
import Model.Factura;
import Model.Proveedor;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;

/**
 *
 * @author PAOLA
 */
public class AbonoProveedorDAO {

    Conexion conex;
    private AbonoProveedor abono;
    private DetalleAbono detalleAbono;
    private ResultSet result;
    private Factura factura;
    private List<AbonoProveedor> listaAbono;
    private List<Factura> listafactura;
    private List<Proveedor> listaProveedor;
    private Statement statement;
    private Connection connection;
    private boolean bandera;

    public AbonoProveedorDAO() {
        listafactura = new ArrayList<>();
        listaProveedor = new ArrayList<>();

    }

    public AbonoProveedorDAO(AbonoProveedor abono) {
        this.abono = abono;
    }

    public AbonoProveedorDAO(Factura factura) {
        this.factura = factura;
    }

    public AbonoProveedorDAO(DetalleAbono detalleAbono) {
        this.detalleAbono = detalleAbono;
    }

    public List<AbonoProveedor> llenarDatos(String sentencia) {
        conex = new Conexion();
        listaAbono = new ArrayList<>();
        if (conex.isEstado()) {
            try {
                result = conex.ejecutarConsulta(sentencia);
                while (result.next()) {
                    listaAbono.add(new AbonoProveedor(result.getString("referencia"),
                            result.getInt("idproveedor"), result.getObject("fecha", LocalDate.class),
                            result.getFloat("pago"), result.getString("periodo"),
                            result.getString("descripcion"), result.getString("nombre")));
                }
                result.close();
                return listaAbono;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conex.cerrarConexion();
            }
        }
        return listaAbono;
    }

    public List<Factura> llenarFacturas(String sentencia) {
        conex = new Conexion();
        listafactura = new ArrayList<>();
        if (conex.isEstado()) {
            try {
                result = conex.ejecutarConsulta(sentencia);
                while (result.next()) {
                    listafactura.add(new Factura(result.getString("nfactura"),
                            result.getFloat("importe"), result.getFloat("pagado"),
                            result.getObject("fecha", LocalDate.class), result.getObject("vencimiento", LocalDate.class),
                            result.getFloat("pendiente")));
                }
                System.out.print(listafactura.size() + "  si hay datosssss");
                result.close();
                return listafactura;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conex.cerrarConexion();
            }
        }
        return listafactura;
    }

    public List<Proveedor> llenarProveedor() {
        if (conex.isEstado()) {
            try {
                String sentencia = "SELECT proveedor.codigo,proveedor.ruc,proveedor.nombre FROM proveedor";
                result = conex.ejecutarConsulta(sentencia);
                while (result.next()) {
                    listaProveedor.add(new Proveedor(
                            result.getString("codigo"),
                            result.getString("ruc"),
                            result.getString("nombre")
                    ));
                }
                result.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conex.cerrarConexion();
            }
        }
        return listaProveedor;
    }

    public void Insertar(AbonoProveedor abonoProveedor) {
        if (conex.isEstado()) {
            try {
                String sentencia =String.format("select insert_abono('%1$s','%2$s','%3$s','%4$s','%5$s') as registro",
                        abonoProveedor.getDetalletipoPago(),abonoProveedor.getDetalletipoBanco(),
                        abonoProveedor.getRuc(),abonoProveedor.getReferencia(),abonoProveedor.getFecha());
                result =conex.ejecutarConsulta(sentencia);
                while (result.next()) {
                    abonoProveedor.setIdAbonoProveedor(result.getInt("registro"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conex.cerrarConexion();
            }
        }
    }
    
    public boolean InsertarDetalle(List<Factura> selectedFactura, AbonoProveedor abono) {
        if (conex.isEstado()) {
            try {
                for (int i = 0; i < selectedFactura.size(); i++) {
                    String sentencia =String.format("select insert_detalleabono(%1$d,'%2$s','%3$s','%4$s')", 
                            abono.getIdAbonoProveedor(),selectedFactura.get(i).getPagado(),
                            abono.getPeriodo(),selectedFactura.get(i).getNfactura());
                    System.out.print(sentencia);
                    result =conex.ejecutarConsulta(sentencia);
                }
                bandera=result.next();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conex.cerrarConexion();
            }
        }
        return bandera;
    }
    
    public void search_date_payment(float importe, AbonoProveedor abonoProveedor) {
        if (conex.isEstado()) {
            try {
                String sentencia =String.format("select search_date_payment('%1$s') as idabono;",importe);
                result =conex.ejecutarConsulta(sentencia);
                while (result.next()) {
                    abonoProveedor.setIdAbonoProveedor(result.getInt("idabono"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conex.cerrarConexion();
            }
        }
    }
    
    public void select_date_payment(int idabono){
        if (conex.isEstado()) {
            try {
                String sentencia = String.format("select * from select_date_payment(%1$d);",idabono);
                result = conex.ejecutarConsulta(sentencia);
                System.out.println(sentencia);
                listaAbono.clear();
                while (result.next()) {
                    listaAbono.add(new AbonoProveedor(result.getInt("idabonopro"),
                    result.getString("referencia"),result.getObject("fecha",LocalDate.class),
                    result.getString("periodo"),result.getString("tipopago"),
                    result.getString("nombre"),result.getString("tipobanco"),
                    result.getString("ruc")));
                }
                System.out.print("Cant Lista: " + listaAbono.size());
                result.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conex.cerrarConexion();
            }
        }
    }
    
    public List<Factura> select_date_invoice(int idabono){
        if (conex.isEstado()) {
            try {
                String sentencia = String.format("select * from select_date_invoice(%1$d);",idabono);
                result = conex.ejecutarConsulta(sentencia);
                System.out.println(sentencia);
                listafactura.clear();
                while (result.next()) {
                    listafactura.add(new Factura(result.getString("nfactura"),result.getFloat("importe"),
                            result.getFloat("pago"),result.getObject("fecha",LocalDate.class),
                            result.getObject("vencimiento", LocalDate.class),result.getFloat("pendiente")));
                }
                System.out.print("Cant Lista: " + listafactura.size());
                result.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conex.cerrarConexion();
            }
        }
        return listafactura;
    }

    public Conexion getConex() {
        return conex;
    }

    public void setConex(Conexion conex) {
        this.conex = conex;
    }

    public AbonoProveedor getAbono() {
        return abono;
    }

    public void setAbono(AbonoProveedor abono) {
        this.abono = abono;
    }

    public ResultSet getResult() {
        return result;
    }

    public DetalleAbono getDetalleAbono() {
        return detalleAbono;
    }

    public void setDetalleAbono(DetalleAbono detalleAbono) {
        this.detalleAbono = detalleAbono;
    }

    public void setResult(ResultSet result) {
        this.result = result;
    }

    public List<AbonoProveedor> getListaAbono() {
        return listaAbono;
    }

    public void setListaAbono(List<AbonoProveedor> listaAbono) {
        this.listaAbono = listaAbono;
    }

    public List<Factura> getListafactura() {
        return listafactura;
    }

    public void setListafactura(List<Factura> listafactura) {
        this.listafactura = listafactura;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

}
