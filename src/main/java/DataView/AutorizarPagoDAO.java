/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataView;

import Controller.Conexion;
import Model.Factura;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Model.AutorizacionPago;

/**
 *
 * @author PAOLA
 */
public class AutorizarPagoDAO {

    Conexion conex = new Conexion();
    private Factura factura;
    private ResultSet result;
    private AutorizacionPago autorizarPago;
    private List<AutorizacionPago> listFactAuto;

    public AutorizarPagoDAO() {

        factura = new Factura();
        listFactAuto = new ArrayList<>();
    }

    public List<AutorizacionPago> llenar() {
        System.out.println("hola");
        if (conex.isEstado()) {
            try {
                String sentencia = "SELECT x.nfactura,x.descripcion,x.importe,x.fecha,x.vencimiento,x.nombre\n"
                        + "			FROM(SELECT f.nfactura,f.descripcion,f.importe,f.fecha,f.vencimiento,f.estado, p.nombre from factura as f \n"
                        + "			INNER JOIN proveedor as p on (f.idproveedor = p.idproveedor) \n"
                        + "			where (f.importe - f.pagado) != 0 and habilitar = 1) AS X WHERE X.estado =0;";
                result = conex.ejecutarConsulta(sentencia);
                while (result.next()) {
                    listFactAuto.add(new AutorizacionPago(result.getString("nfactura"),
                            result.getString("descripcion"), result.getFloat("importe"),
                            result.getObject("fecha", LocalDate.class),
                            result.getObject("vencimiento", LocalDate.class), result.getString("nombre")));
                }
                System.out.println(listFactAuto.size() + " :DATOS");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conex.cerrarConexion();
            }
        }
        return listFactAuto;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public ResultSet getResult() {
        return result;
    }

    public void setResult(ResultSet result) {
        this.result = result;
    }

    public AutorizacionPago getAutorizarPago() {
        return autorizarPago;
    }

    public void setAutorizarPago(AutorizacionPago autorizarPago) {
        this.autorizarPago = autorizarPago;
    }

    public List<AutorizacionPago> getListFactAuto() {
        return listFactAuto;
    }

    public void setListFactAuto(List<AutorizacionPago> listFactAuto) {
        this.listFactAuto = listFactAuto;
    }

}
