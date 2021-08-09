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
                        + "f.importe,f.pagado,f.fecha,f.vencimiento,f.estado, p.nombre "
                        + "from factura as f INNER JOIN proveedor as p on (f.idproveedor = p.idproveedor)";
                result = conexion.ejecutarConsulta(sentencia);
                System.out.println("Factura: "+result.toString());
                while (result.next()) {
                    listaFacturas.add(new Factura(result.getInt("idfactura"),
                            result.getString("nfactura"), result.getString("descripcion"),
                            result.getFloat("importe"), result.getFloat("pagado"),
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

}
