/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataView;

import Controller.Conexion;
import Model.Condiciones;
import Model.Proveedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ninat
 */
public class BuscarProvDAO {

    Conexion conexion = new Conexion();
    private Proveedor proveedor;
    private Condiciones condiciones;
    private ResultSet result;
    private List<Proveedor> listaProveedor;

    public BuscarProvDAO() {
        conexion = new Conexion();
        listaProveedor = new ArrayList<>();
    }

    public BuscarProvDAO(Proveedor proveedor) {
        conexion = new Conexion();
        this.proveedor = proveedor;
    }
    
        public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }    

    public List<Proveedor> getListaProveedor() {
        return listaProveedor;
    }

    public void setListaProveedor(List<Proveedor> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }
    
    public List<Proveedor> llenar() {
        if (conexion.isEstado()) {
            try {
                String sentencia = "select p.idproveedor, p.codigo, p.nombre, p.ruc, c.cantdiasvencidos "
                        +" from proveedor p inner join condiciones c on " +
                        "(c.idproveedor = p.idproveedor);";
                result = conexion.ejecutarConsulta(sentencia);
                while (result.next()) {
                    listaProveedor.add(new Proveedor(result.getInt("idproveedor"),
                            result.getString("codigo"),result.getString("ruc"), 
                            result.getString("nombre"),result.getInt("cantdiasvencidos")));           
                }
                System.out.print(sentencia);
                result.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }
        return listaProveedor;
    }

}
