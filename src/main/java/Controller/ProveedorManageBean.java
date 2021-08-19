/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataView.CondicionesDAO;
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
     private CondicionesDAO condicionesDAO;
     private Proveedor p;

     public Proveedor getP() {
          return p;
     }

     public void setP(Proveedor p) {
          this.p = p;
     }

     public Condiciones getCondiciones() {
          return condiciones;
     }

     public void setCondiciones(Condiciones condiciones) {
          this.condiciones = condiciones;
     }

     public CondicionesDAO getCondicionesDAO() {
          return condicionesDAO;
     }

     public void setCondicionesDAO(CondicionesDAO condicionesDAO) {
          this.condicionesDAO = condicionesDAO;
     }
     private List<Proveedor> listaProveedor;
     private List<Proveedor> Proveedores;
     private Proveedor selectedProveedor;
     private String msj;
     private String nom;
     private String cod;

     public ProveedorManageBean() {
          condiciones = new Condiciones();
          condicionesDAO = new CondicionesDAO();
          listaProveedor = new ArrayList<>();
          proveedorDAO = new ProveedorDAO();
          proveedor= new Proveedor();
          p=new Proveedor();
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
          } else {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
          }

          PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
          PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
     }

     public void cargarEditar(Proveedor p, Condiciones c) {
          this.proveedor.setIdProveedor(p.getIdProveedor());
          this.proveedor.setCodigo(p.getCodigo());
          this.proveedor.setNombre(p.getNombre());
          this.proveedor.setDireccion(p.getDireccion());
          this.proveedor.setContacto(p.getContacto());
          this.proveedor.setWebPage(p.getWebPage());
          this.proveedor.setRuc(p.getRuc());
          this.proveedor.setRazonSocial(p.getRazonSocial());
          this.proveedor.setTelefono(p.getTelefono());
          this.proveedor.setEmail(p.getEmail());
          this.proveedor.setEstado(p.isEstado());
          //condiciones 
          this.condiciones.setCantDiasVencidos(c.getCantDiasVencidos());
          this.condiciones.setDescuento(c.getDescuento());
          this.condiciones.setDiasNeto(c.getDiasNeto());
          this.condiciones.setDiasDescuento(c.getDiasDescuento());
          this.condiciones.setDescripcion(c.getDescripcion());
     }

     public void editar() {
          try {

               System.out.println("ENTRANDO A  EDITAR PROVEEDOR: ");
               this.proveedorDAO.update(proveedor, this.proveedor.getCodigo());
               System.out.println("SALIENDO PROVEEDOR: ");
                this.condiciones.setProveedor(this.proveedor);
                this.condicionesDAO.updateCondiciones(condiciones);

               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Proveedor Guardado"));

          } catch (Exception e) {
               System.out.println("ERROR DAO EDITAR PROVEEDOR: " + e);
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error al guardar"));

          }
          PrimeFaces.current().executeScript("PF('manageProductDialogEdit').hide()");
          PrimeFaces.current().executeScript("location.reload()");

     }

     public void insertar() {
          try {
               System.out.println("INSERTADO  PROVEEDOR");
               System.out.println(proveedor.getCodigo()); 
               System.out.println(this.proveedor.getCodigo());
               this.proveedorDAO.insertarp(proveedor);
               System.out.println("INSERTADO  CONDICIONES");
               condicionesDAO.insertarCondiciones(condiciones);
               

               System.out.print("termina metodo DAO insertar condiciones");
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Proveedor agg"));

          } catch (Exception e) {
               System.out.println("ERROR DAO PROVEEDOR: " + e);
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error al guardar"));

          }
          PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
          PrimeFaces.current().executeScript("location.reload()");

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
     public void aleatorioCod(){
          String uuid = java.util.UUID.randomUUID().toString().substring(4,7).toUpperCase();
          String uuid2 = java.util.UUID.randomUUID().toString().substring(4,7);
         this.proveedor.setCodigo("ERP-"+uuid+uuid2);
         
     }

     //Metodos primeFaces
     public void openNew() {
          this.selectedProveedor = new Proveedor();
          aleatorioCod();
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
