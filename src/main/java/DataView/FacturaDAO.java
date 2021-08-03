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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ninat
 */
public class FacturaDAO {

    Conexion conexion;
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

    public int insertar() {
        String sentencia = "INSERT INTO public.factura(nfactura, descripcion,"
                + " importe, pagado, fecha, vencimiento, estado, idproveedor,"
                + " idasiento) VALUES (" + factura.getNfactura() + ",'" 
                + factura.getDescripcion() + "',"+ factura.getImporte() + "," 
                + factura.getPagado() + "," + factura.getFecha() + ","
                + factura.getVencimiento() + "," + factura.getEstado() + ","
                + factura.getIdproveedor() + "," + factura.getIdasiento() + ")";
        if (conexion.isEstado()) {
            return conexion.insertar(sentencia);
        }
        return -1;
    }

    public List<Factura> llenar() {
        if (conexion.isEstado()) {
            try {
                String sentencia = "SELECT * FROM public.factura";
                result = conexion.ejecutarConsulta(sentencia);
                System.out.println(result.toString());
                while (result.next()) {
                    listaFacturas.add(new Factura(result.getInt("idfactura"),
                        result.getInt("nfactura"),result.getString("descripcion"),
                        result.getFloat("importe"),result.getFloat("pagado"),
                    result.getDate("fecha"),result.getDate("vencimiento"),
                    result.getInt("estado"),result.getInt("idproveedor"),
                    result.getInt("idasiento")));
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
}
