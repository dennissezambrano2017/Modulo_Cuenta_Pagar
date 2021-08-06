/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import DataView.BuscarProvDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import Model.Proveedor;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.event.SelectEvent;
/**
 *
 * @author ninat
 */
@ManagedBean(name = "BuscarMB")
@ViewScoped
public class BuscarProvManagedBean implements Serializable {

     private Proveedor proveedor;
     private BuscarProvDAO buscarprovDAO;
     private List<Proveedor> listaProveedor;
     private String nom;
     private String cod;
     
     
     
     public BuscarProvManagedBean() {
          proveedor = new Proveedor();
          listaProveedor = new ArrayList<>();
          buscarprovDAO = new BuscarProvDAO();
          listaProveedor = buscarprovDAO.llenar();

     }
     
     public Proveedor getProveedor() {
          return proveedor;
     }

     public void setProveedor(Proveedor proveedor) {
          this.proveedor = proveedor;
     }

     public BuscarProvDAO getBuscarProvDAO() {
          return buscarprovDAO;
     }

     public void setBuscarProvDAO(BuscarProvDAO buscarprovDAO) {
          this.buscarprovDAO = buscarprovDAO;
     }

     public List<Proveedor> getListaProveedor() {
          return listaProveedor;
     }

     public void setListaProveedor(List<Proveedor> listaProveedor) {
          this.listaProveedor = listaProveedor;
     }
     
     
     public void onRowSelect(SelectEvent<Proveedor> event) {
        String msg2 = event.getObject().getNombre();
        String msg3 = event.getObject().getRuc();
        System.out.print("Nombre: "+msg2);
        System.out.print("Ruc: "+msg3);
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

}
