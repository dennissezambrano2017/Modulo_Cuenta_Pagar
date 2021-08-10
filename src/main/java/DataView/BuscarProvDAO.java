/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataView;

import Controller.Conexion;
import Model.Factura;
import Model.Proveedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ninat
 */
public class BuscarProvDAO {

    Conexion conexion = new Conexion();
    private Proveedor proveedor;
    private Factura factura;
    private ResultSet result;
    private List<Proveedor> listaProveedor;
    private List<Factura> listaFactura = new ArrayList<>();

    public BuscarProvDAO() {
        conexion = new Conexion();
        listaProveedor = new ArrayList<>();
    }

    public BuscarProvDAO(Proveedor proveedor) {
        conexion = new Conexion();
        this.proveedor = proveedor;
    }

    public List<Factura> getListaFactura() {
        return listaFactura;
    }

    public void setListaFactura(List<Factura> listaFactura) {
        this.listaFactura = listaFactura;
    }

    public List<Proveedor> llenar() {
        if (conexion.isEstado()) {
            try {
                String sentencia = "SELECT * from proveedor";
                result = conexion.ejecutarConsulta(sentencia);
                while (result.next()) {
                    listaProveedor.add(new Proveedor(result.getString("codigo"),
                            result.getString("ruc"), result.getString("nombre")));
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

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<Proveedor> getListaProveedor() {
        return listaProveedor;
    }

    public void setListaProveedor(List<Proveedor> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }

    //Diana:Utiliza esta función 
    public String Buscar(String nfactura) {
        if (conexion.isEstado()) {
            try {
                String sentencia = "SELECT * from proveedor where idproveedor ="
                        + "(SELECT  idproveedor from factura where nfactura = '"
                        + nfactura + "')";
                result = conexion.ejecutarConsulta(sentencia);
                while (result.next()) {
                    listaProveedor.add(new Proveedor(result.getString("codigo"),
                            result.getString("ruc"), result.getString("nombre")));
                }
                result.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }
        return listaProveedor.get(0).getRuc();
    }
}
