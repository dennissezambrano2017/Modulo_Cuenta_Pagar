/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataView.AbonoProveedorDAO;
import Model.AbonoProveedor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;


/**
 *
 * @author PAOLA
 */
@ManagedBean(name = "abonoProveedorMB")
@ViewScoped
public class AbonoProveedorManagedBean implements Serializable{
    private AbonoProveedor abonoproveedor;
    private AbonoProveedorDAO abonoDAO;
    private List<AbonoProveedor> listaAbonos;
    
    public AbonoProveedorManagedBean() {
        abonoproveedor = new AbonoProveedor();
        listaAbonos = new ArrayList<>();
        abonoDAO = new AbonoProveedorDAO();
        listaAbonos = abonoDAO.llenar();
     
    }
    public void mostrar()
    {
        listaAbonos = abonoDAO.llenar();
        System.out.println(listaAbonos);
    }

    public AbonoProveedor getAbonoproveedor() {
        return abonoproveedor;
    }

    public void setAbonoproveedor(AbonoProveedor abonoproveedor) {
        this.abonoproveedor = abonoproveedor;
    }

    public AbonoProveedorDAO getAbonoDAO() {
        return abonoDAO;
    }

    public void setAbonoDAO(AbonoProveedorDAO abonoDAO) {
        this.abonoDAO = abonoDAO;
    }

    public List<AbonoProveedor> getListaAbonos() {
        return listaAbonos;
    }

    public void setListaAbonos(List<AbonoProveedor> listaAbonos) {
        this.listaAbonos = listaAbonos;
    }
    
    
}
