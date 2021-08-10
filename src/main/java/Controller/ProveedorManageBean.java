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
import Model.Condiciones;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author ebert
 */
@ManagedBean(name = "proveedorDAO")
@ViewScoped
public class ProveedorManageBean implements Serializable {

     private Proveedor proveedor;
     private Condiciones condiciones;
     private ProveedorDAO proveedorDAO;
     private List<Proveedor> listaProveedor;
     private List<Proveedor> Proveedores;
     private Proveedor selectedProveedor;
     private String msj;
     private String nom;
     private String cod;

     public ProveedorManageBean() {
          proveedor = new Proveedor();
          condiciones =new Condiciones();
          listaProveedor = new ArrayList<>();
          proveedorDAO = new ProveedorDAO();
          listaProveedor = proveedorDAO.llenar();

     }

     public List<Proveedor> getProveedores() {
          return Proveedores;
     }

     public void setProveedores(List<Proveedor> Proveedores) {
          this.Proveedores = Proveedores;
     }

     public Proveedor getSelectedProveedor() {
          return selectedProveedor;
     }

     public void setSelectedProveedor(Proveedor selectedProveedor) {
          this.selectedProveedor = selectedProveedor;
     }
     
      public void saveProduct() {
        if (this.selectedProveedor.getCodigo() == null) {
            this.selectedProveedor.setCodigo(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            this.listaProveedor.add(this.selectedProveedor);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }


     
     public void insertar(){
        try{
             this.proveedorDAO.insertar(proveedor,condiciones);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Proveedor agg"));
             
        }  catch(Exception e){
              System.out.println("ERROR DAO: " + e);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error al guardar"));
              
             
        }
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
          try {
               this.proveedorDAO = new ProveedorDAO();
               this.listaProveedor = this.proveedorDAO.llenar();
          } catch (Exception e) {
               throw e;
          }
          return listaProveedor;
     }

     public void setListaProveedor(List<Proveedor> listaProveedor) {
          this.listaProveedor = listaProveedor;
     }

     public void onRowSelect(SelectEvent<Proveedor> event) {
          String msg2 = event.getObject().getNombre();
          String msg3 = event.getObject().getCodigo();
          System.out.print("Nombre: " + msg2);
          System.out.print("Codigo: " + msg3);
          setNom(msg2);
          setCod(msg3);
     }

     public String getNom() {
          return nom;
     }

     public void setNom(String nom) {
          this.nom = nom;
     }

     public String getCod() {
          return cod;
     }

     public void setCod(String cod) {
          this.cod = cod;
     }
      
     //Metodos primeFaces
     public void openNew() {
        this.selectedProveedor = new Proveedor();
    }
    public void deleteProduct() {
        this.Proveedores.remove(this.selectedProveedor);
        this.selectedProveedor = null;
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
         PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedProducts()) {
            int size = this.listaProveedor.size();
            return size > 1 ? size + " products selected" : "1 product selected";
        }

        return "Delete";
    }

    public boolean hasSelectedProducts() {
        return this.listaProveedor != null && !this.listaProveedor.isEmpty();
    }

    public void deleteSelectedProducts() {
        this.Proveedores.removeAll(this.listaProveedor);
        this.listaProveedor = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

}
