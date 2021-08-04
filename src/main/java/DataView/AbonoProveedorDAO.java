/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataView;

import Controller.Conexion;
import Model.AbonoProveedor;
import Model.DetalleAbono;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PAOLA
 */
public class AbonoProveedorDAO {

    Conexion conex;
    private AbonoProveedor abono;
    private ResultSet result;
    private List<AbonoProveedor> listaAbono;

    public AbonoProveedorDAO() {
        conex = new Conexion();
        listaAbono = new ArrayList<>();
    }

    public AbonoProveedorDAO(AbonoProveedor abono) {
        conex = new Conexion();
        this.abono = abono;
    }

    public List<AbonoProveedor> llenar() {
        if (conex.isEstado()) {
            try {
                String sentencia = "SELECT a.fecha,pag.descripcion,a.referencia,sum(d.pago) as Pago,a.\"idProveedor\",p.nombre,d.periodo\n"
                        + "FROM \"public\".\"abonoProveedor\" a \n"
                        + "	INNER JOIN \"public\".\"detalleAbono\" d ON ( a.\"idAbonoProveedor\" = d.\"idAbonoProveedor\"  )  \n"
                        + "	INNER JOIN \"public\".\"tipoPago\" t ON ( a.\"idTipoPago\" = t.\"idTipoPago\"  )  \n"
                        + "	INNER JOIN \"public\".proveedor p ON ( a.\"idProveedor\" = p.idproveedor  )  \n"
                        + "	INNER JOIN \"public\".\"tipoPago\" pag ON ( a.\"idTipoPago\" = pag.\"idTipoPago\"  )  \n"
                        + "	group by d.periodo,a.fecha,pag.descripcion,a.referencia,a.\"idProveedor\",p.nombre";
                result = conex.ejecutarConsulta(sentencia);
                while (result.next()) {
                    listaAbono.add(new AbonoProveedor(result.getString("referencia"),
                            result.getInt("idProveedor"),result.getDate("fecha"),
                            result.getFloat("Pago"),result.getString("periodo"),
                            result.getString("descripcion"),result.getString("nombre")));
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

}
