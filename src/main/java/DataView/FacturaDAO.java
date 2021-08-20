/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataView;

import Controller.Conexion;
import java.sql.Connection;
import Model.Factura;
import Model.Proveedor;
import java.sql.PreparedStatement;
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
        factura = new Factura();
        conexion = new Conexion();
        listaFacturas = new ArrayList<>();
    }

    public FacturaDAO(Factura factura) {
        conexion = new Conexion();
        this.factura = factura;
    }

    public List<Factura> llenarP(String n) {
        if (conexion.isEstado()) {
            try {
                String sentencia = "SELECT f.idfactura,f.nfactura,f.descripcion,"
                        + "f.importe,f.pagado ,(f.importe - f.pagado) as pendiente,f.fecha,"
                        + "f.vencimiento,f.estado, p.nombre, f.habilitar from factura as f "
                        + "INNER JOIN proveedor as p on (f.idproveedor = p.idproveedor) "
                        + "where (f.importe - f.pagado) != 0 and habilitar = " + n;
                result = conexion.ejecutarConsulta(sentencia);
                System.out.println("Factura: " + result.toString());
                while (result.next()) {
                    listaFacturas.add(new Factura(result.getInt("idfactura"),
                            result.getString("nfactura"), result.getString("descripcion"),
                            result.getFloat("importe"), result.getFloat("pagado"),
                            result.getFloat("pendiente"),
                            result.getObject("fecha", LocalDate.class),
                            result.getObject("vencimiento", LocalDate.class),
                            result.getInt("estado"), result.getString("nombre"),
                            result.getInt("habilitar")));
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }
        return listaFacturas;
    }

    public List<Factura> llenar() {
        if (conexion.isEstado()) {
            try {
                String sentencia = "SELECT f.idfactura,f.nfactura,f.descripcion,"
                        + "f.importe,f.pagado ,(f.importe - f.pagado) as pendiente,f.fecha,"
                        + "f.vencimiento,f.estado, p.nombre, f.habilitar from factura as f "
                        + "INNER JOIN proveedor as p on (f.idproveedor = p.idproveedor) "
                        + "where (f.importe - f.pagado) != 0 and habilitar = 1";
                result = conexion.ejecutarConsulta(sentencia);
                System.out.println("Factura: " + result.toString());
                while (result.next()) {
                    listaFacturas.add(new Factura(result.getInt("idfactura"),
                            result.getString("nfactura"), result.getString("descripcion"),
                            result.getFloat("importe"), result.getFloat("pagado"),
                            result.getFloat("pendiente"),
                            result.getObject("fecha", LocalDate.class),
                            result.getObject("vencimiento", LocalDate.class),
                            result.getInt("estado"), result.getString("nombre"),
                            result.getInt("habilitar")));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }
        return listaFacturas;
    }

    public List<Factura> llenarDetalle(String n) {
        System.out.println("HOLA LLENAR DETALLE: " + n);
        listaFacturas.clear();
        System.out.println("CANTIDAD DETALLE LLENAR: " + listaFacturas.size());
        if (conexion.isEstado()) {
            try {
                String sentencia = "select * from detalle_compra where nfactura = '" + n + "';";
                result = conexion.ejecutarConsulta(sentencia);
                System.out.println("Factura: " + result.toString());
                while (result.next()) {
                    listaFacturas.add(new Factura(result.getFloat("importe"),
                            result.getString("detalle"), result.getString("iddetallecompra")));
                }
                System.out.print(sentencia);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }
        System.out.println("CANTIDAD DETALLE LLENAR2: " + listaFacturas.size());
        return listaFacturas;
    }

    public void Insertar(Factura factura) {
        if (conexion.isEstado()) {
            try {
                String cadena = "INSERT INTO public.factura("
                        + "nfactura, descripcion, importe, pagado, fecha, vencimiento,idproveedor,habilitar,estado)"
                        + " VALUES ('" + factura.getNfactura() + "','"
                        + factura.getDescripcion() + "'," + factura.getImporte() + ","
                        + factura.getPagado() + ",'" + factura.getFecha() + "','"
                        + factura.getVencimiento() + "',(Select idproveedor from proveedor p "
                        + " where p.ruc = '" + factura.getRuc() + "'), 1,0)";
                System.out.println(cadena);
                conexion.Ejecutar2(cadena);
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }
    }

    //Insertar Detalle
    public void insertdetalle(List<Factura> selectedFactura, Factura factura) {
        System.out.println("HOLA ESTOY EN MANAGE HABILITAR");
        if (conexion.isEstado()) {
            try {
                for (int i = 0; i < selectedFactura.size(); i++) {
                    String cadena = "SELECT public.insertdetallefac('"
                            + factura.getNfactura() + "',"
                            + selectedFactura.get(i).getImporteD() + ", '"
                            + selectedFactura.get(i).getDetalle() + "')";
                    System.out.println(cadena);
                    conexion.Ejecutar2(cadena);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }

    }

    //Actualizar factura
    public void Actualizar(Factura factura) {
        if (conexion.isEstado()) {
            try {
                String cadena = "select actualizarfactura('" + factura.getNfactura()
                        + "','" + factura.getDescripcion() + "'," + factura.getImporte()
                        + "," + factura.getPagado()
                        + ",'" + factura.getFecha()
                        + "','" + factura.getVencimiento()
                        + "','" + factura.getRuc() + "')";
                System.out.println(cadena);
                conexion.Ejecutar2(cadena);
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }
    }

    public void Actdetalle(List<Factura> selectedFactura) {
        if (conexion.isEstado()) {
            try {
                for (int i = 0; i < selectedFactura.size(); i++) {
                    String cadena = "SELECT public.actualizardetallefac('"
                            + selectedFactura.get(i).getId_detalle() + "',"
                            + selectedFactura.get(i).getImporteD() + ",'"
                            + selectedFactura.get(i).getDetalle() + "')";
                    System.out.println(cadena);
                    conexion.Ejecutar2(cadena);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }
    }

    public void dhabilitar(String d, int n) {
        System.out.println("HOLA ESTOY EN MANAGE HABILITAR UNO");
        if (conexion.isEstado()) {
            try {
                String cadena = "select habilitarfactura(" + n + ",'" + d + "')";
                System.out.println(cadena);
                conexion.Ejecutar2(cadena);
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }
    }

    //Consultas
    public float buscarPagado(String nfactura) {
        float pagado = 0;
        if (conexion.isEstado()) {
            try {
                String sentencia = "select pagado from factura where nfactura= '"
                        + nfactura + "'";
                result = conexion.ejecutarConsulta(sentencia);
                while (result.next()) {
                    pagado = result.getFloat("pagado");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }
        return pagado;
    }

    public String Buscar(String nfactura) {
        String r = "";
        if (conexion.isEstado()) {
            try {
                String sentencia = "SELECT ruc from proveedor where idproveedor ="
                        + "(SELECT  idproveedor from factura where nfactura = '"
                        + nfactura + "')";
                result = conexion.ejecutarConsulta(sentencia);
                while (result.next()) {
                    r = result.getString("ruc");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " error en conectarse");
            } finally {
                conexion.cerrarConexion();
            }
        }
        return r;
    }

//Paola: Funcion para autorizar Pago
    public void AutorizarPago(String sentencia) {
        result = conexion.ejecutarConsulta(sentencia);
    }
//    public void AutorizarPago(String sentencia) {
//        result= conexion.ejecutarConsulta(sentencia);
//        System.out.println(result.getRow());
//    }

//    public int Autorizar(String sentencia) {
//        try {
//            connection = conexion.getCnx();
//            statement = connection.createStatement();
//            statement.executeUpdate(sentencia);
//            System.out.print("Si inserto");
//            return 1;
//        } catch (SQLException err) {
//            System.out.println(err + " Error de insertar");
//            return 0;
//        }
//    }
    
    // metodos aux para comunicación con la db
    public static Factura getOneFactura(int id) {
        Factura fac = new Factura();
        
        Conexion conn = new Conexion();
        String query = "select idfactura, nfactura, descripcion, importe, pagado, fecha, vencimiento, estado, idproveedor, idasiento\n" +
                            "from factura\n" +
                            "where \"idfactura\"=?;";
        try {
            conn.abrirConexion();
            
            //Statement stmt = conn.conex.createStatement();
            PreparedStatement stmt = conn.conex.prepareStatement(query);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                fac.setId(rs.getInt("idfactura"));
                fac.setNfactura(rs.getString("nfactura"));
                fac.setDescripcion(rs.getString("descripcion"));
                fac.setImporte(rs.getFloat("importe"));
                fac.setPagado(rs.getFloat("pagado"));
                fac.setFecha(rs.getObject("fecha", LocalDate.class));
                fac.setVencimiento(rs.getObject("vencimiento", LocalDate.class));
                fac.setEstado(rs.getInt("estado"));
                fac.setIdproveedor(rs.getInt("idproveedor"));
                fac.setIdasiento(rs.getInt("idasiento"));
            }
            conn.conex.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

        return fac;
    }
    
    // Trae los datos de la base de datos incluido los de los proveedores
    public static List<Factura> get_fac_pro(LocalDate desde, LocalDate hasta, int opcion) {
        List<Factura> lista = new ArrayList<>();
        
        Conexion conn = new Conexion();
        String query = "select * from select_fac_pro(?, ?, ?);";
        
        try {
            conn.abrirConexion();
            
            //Statement stmt = conn.conex.createStatement();
            PreparedStatement stmt = conn.conex.prepareStatement(query);
            // Establecemos los argumentos.
            stmt.setDate(1, java.sql.Date.valueOf(desde));
            stmt.setDate(2, java.sql.Date.valueOf(hasta));
            stmt.setInt(3, opcion);
            //stmt.setObject(1, new java.sql.Date());
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Factura fac = new Factura();
                fac.setProveedor(new Proveedor());
                
                fac.setFecha(rs.getObject("fecha", LocalDate.class));
                fac.setId(rs.getInt("id_factura"));
                fac.setIdasiento(rs.getInt("id_asiento"));
                fac.setNfactura(rs.getString("nfactura"));
                fac.setDescripcion(rs.getString("descripcion"));
                fac.setImporte(rs.getFloat("importe"));
                fac.setPagado(rs.getFloat("pagado"));
                fac.setVencimiento(rs.getObject("vencimiento", LocalDate.class));
                fac.setEstado(rs.getInt("estado"));
                fac.setIdproveedor(rs.getInt("id_proveedor"));
                
                fac.setPor_pagar(fac.getImporte()-fac.getPagado()); // Calculado
                
                //fac.proveedor.idProveedor = rs.getInt("id_proveedor");
                //fac.proveedor.nombre = rs.getString("nombre");
                
                fac.getProveedor().setIdProveedor(rs.getInt("id_proveedor"));
                fac.getProveedor().setNombre(rs.getString("nombre"));
                
                fac.setEstado_string(rs.getString("estado_string"));
                
                lista.add(fac);
            }
            conn.conex.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

        return lista;
    }
}
