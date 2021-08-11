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
import Model.TipoPago;
import Model.TipoBanco;
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
    private Statement statement;
    private Connection connection;

    public AbonoProveedorDAO() {
        conex = new Conexion();
        listaAbono = new ArrayList<>();
        listafactura = new ArrayList<>();
    }

    public AbonoProveedorDAO(AbonoProveedor abono) {
        conex = new Conexion();
        this.abono = abono;
    }

    public AbonoProveedorDAO(Factura factura) {
        conex = new Conexion();
        this.factura = factura;
    }

    public AbonoProveedorDAO(DetalleAbono detalleAbono) {
        conex = new Conexion();
        this.detalleAbono = detalleAbono;
    }

    public List<AbonoProveedor> llenar() {
        if (conex.isEstado()) {
            try {
                String sentencia = "SELECT a.fecha,pag.descripcion,a.referencia,sum(d.pago) as Pago,a.idproveedor,p.nombre,d.periodo\n"
                        + "FROM abonoproveedor a \n"
                        + "	INNER JOIN detalleabono d ON ( a.idabonoproveedor = d.idabonoproveedor  )  \n"
                        + "	INNER JOIN tipopago t ON ( a.idtipopago= t.idtipopago  )  \n"
                        + "	INNER JOIN proveedor p ON ( a.idproveedor = p.idproveedor  )  \n"
                        + "	INNER JOIN tipopago pag ON ( a.idtipopago = pag.idtipopago  )  \n"
                        + "	group by d.periodo,a.fecha,pag.descripcion,a.referencia,a.idproveedor,p.nombre";
                result = conex.ejecutarConsulta(sentencia);
                while (result.next()) {
                    listaAbono.add(new AbonoProveedor(result.getString("referencia"),
                            result.getInt("idproveedor"), result.getDate("fecha"),
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

    public List<Factura> llenar(String sentencia) {
        if (conex.isEstado()) {
            try {
                result = conex.ejecutarConsulta(sentencia);
                while (result.next()) {
                    listafactura.add(new Factura(result.getString("nfactura"),
                             result.getFloat("importe"), result.getFloat("pagado"),
                             result.getObject("fecha", LocalDate.class), result.getObject("vencimiento", LocalDate.class),
                             result.getFloat("pendiente")));
                }

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

    public int insertar(String sentencia) {
        try {
            connection = conex.getCnx();
            statement = connection.createStatement();
            statement.executeUpdate(sentencia);
            System.out.print("Si insertoq");
            return 1;
        } catch (Exception e) {
            return 0;
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
