/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataView;

import Controller.Conexion;
import Model.AbonoProveedor;
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
                String sentencia = "select * from public.\"abonoProveedor\"";
                result = conex.ejecutarConsulta(sentencia);
                while (result.next()) {
                    listaAbono.add(new AbonoProveedor(result.getInt("idAbonoProveedor"),
                            result.getString("referencia"), result.getInt("idAsiento"),
                            result.getInt("idTipoPago"), result.getInt("idTipoBanco"), 
                            result.getInt("idProveedor")));
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
