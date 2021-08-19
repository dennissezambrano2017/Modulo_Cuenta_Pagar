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
import javax.faces.context.FacesContext;

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
                String cadena = "INSERT INTO public.abonoproveedor(\n"
                        + "	idabonoproveedor, idtipopago, idtipobanco, idproveedor, "
                        + "     referencia, idasiento, fecha)\n"
                        + "	VALUES('" + abonoProveedor.getIdAbonoProveedor()+ "','"
                        + factura.getDescripcion() + "',"
                        + "(select tp.idtipopago from public.tipopago tp where tp.descripcion='" + factura.getImporte() + "'),"
                        + "(select tb.idtipobanco from public.tipobanco tb where tb.descripcion='"+factura.getPagado() + "'),'" + factura.getFecha() + "','"
                        + factura.getVencimiento() + "',(Select idproveedor from proveedor p "
                        + " where p.ruc = '" + factura.getRuc() + "'), 1)";
                System.out.print(cadena);
                conex.Ejecutar2(cadena);
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conex.cerrarConexion();
            }
        }
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
