/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import Model.Proveedor;
import DataView.ProveedorDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author ebert
 */
@ManagedBean(name = "proveedorDAO")
@ViewScoped
public class ProveedorManageBean implements Serializable {

     private Proveedor proveedor;
     private ProveedorDAO proveedorDAO;
     private List<Proveedor> listaProveedor;
     String msj;

     public ProveedorManageBean() {
          proveedor = new Proveedor();
          listaProveedor = new ArrayList<>();
          proveedorDAO = new ProveedorDAO();
          listaProveedor = proveedorDAO.llenar();

     }

     public void mostrar() {
          listaProveedor = proveedorDAO.llenar();

     }

     public void insertarProveedor() {
          try {
               this.proveedorDAO = new ProveedorDAO();
               this.proveedorDAO.Insertar(proveedor);
               this.proveedor = new Proveedor();
          } catch (Exception e) {
               this.msj = "Error :" + e.getMessage();
               System.out.println(msj+"ERROR DAO");
          }
          FacesMessage mensaje = new FacesMessage(msj);
          FacesContext.getCurrentInstance().addMessage(msj, mensaje);
     }

     public Proveedor getProveedor() {
          return proveedor;
     }

     public void setProveedor(Proveedor proveedor) {
          this.proveedor = proveedor;
     }

     public ProveedorDAO getProveedorDAO() {
          return proveedorDAO;
     }

     public void setProveedorDAO(ProveedorDAO proveedorDAO) {
          this.proveedorDAO = proveedorDAO;
     }

     public List<Proveedor> getListaProveedor() {
          return listaProveedor;
     }

     public void setListaProveedor(List<Proveedor> listaProveedor) {
          this.listaProveedor = listaProveedor;
     }

}
