package Controller;

import DataView.AnticipoDAO;
import Model.Anticipo;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author elect
 * pagina que va a interacturar con ajax
 */

@ManagedBean(name = "anticipoMB")
@ViewScoped
public class AnticipoMB implements Serializable {

    private List<Anticipo> anticipos;
    private Anticipo selected_anticipo;
    
    public AnticipoMB() {
        //this.anticipos = Anticipo.getAll(); // trae solo los datos de los anticipos
        //Anticipo.GetAllDBProveedor(this.anticipos); // trae los proveedores de cada anticipo.
        
        //this.selected_anticipo = new Anticipo();
        //this.selected_anticipo.setFecha(new Date());
        //this.selected_anticipo.setDescripcion("");
        //this.selected_anticipo.setImporte(0.0);
        
        
    }
    
    @PostConstruct
    public void init() {
        this.anticipos = AnticipoDAO.getAllJson();
        
        this.selected_anticipo = new Anticipo();
        this.selected_anticipo.setId_proveedor(1);
        this.selected_anticipo.setImporte(0.0);
        this.selected_anticipo.setFecha(new Date());
    }

    public List<Anticipo> getAnticipos() {
        return anticipos;
    }

    public void setAnticipos(List<Anticipo> anticipos) {
        this.anticipos = anticipos;
    }

    public Anticipo getSelected_anticipo() {
        return selected_anticipo;
    }

    public void setSelected_anticipo(Anticipo selected_anticipo) {
        System.out.println("Descripcion: ");
        System.out.println(selected_anticipo.getDescripcion());
        this.selected_anticipo = selected_anticipo;
    }

    
    
    // metodos aux
    
    public void open_new() {
        this.selected_anticipo = new Anticipo();
        
        this.selected_anticipo.setId_proveedor(2);
        this.selected_anticipo.setImporte(0.0);
        this.selected_anticipo.setFecha(new Date());
        this.selected_anticipo.setDescripcion("");
        
        System.out.println("Nuevo anticipo");
        System.out.println(this.selected_anticipo.getDescripcion());
    }
    
    public void guardar_anticipo() {
        System.out.println("guardar");
        try {
            System.out.println(this.selected_anticipo.getId_anticipo());
            System.out.println(this.selected_anticipo.getDescripcion());
            
            if (this.selected_anticipo.getId_anticipo().isEmpty()){
                this.selected_anticipo.InsertDB();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Anticipo guardado"));
            } else {
                this.selected_anticipo.UpdateDB();
                System.out.println("update registro: " + this.selected_anticipo.getId_anticipo());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Anticipo actualizado"));
            }
            
            
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error al guardar el anticipo");
        }
        
        
        this.anticipos = AnticipoDAO.getAllJson();  // Actualiza los datos de la tabla
        
        PrimeFaces.current().executeScript("PF('manageAnticipoDialog').hide()");
        PrimeFaces.current().ajax().update(":form:dt_anticipos");
        //PrimeFaces.current().executeScript("location.reload()");
    }
    
    public void delete_anticipo(Anticipo seleAnticipo) {
        System.out.println("Anticipo por argumento");
        System.out.println(seleAnticipo.getId_anticipo());
        System.out.println(seleAnticipo.getDescripcion());
        System.out.println(seleAnticipo.getFecha());
        System.out.println("Anticipo por argumento fin");
        
        this.selected_anticipo = seleAnticipo;
        
        try {
            this.selected_anticipo.deleteDB();
        } 
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        this.anticipos = AnticipoDAO.getAllJson();  // Actualiza los datos de la tabla
        PrimeFaces.current().ajax().update(":form:dt_anticipos");
        
        PrimeFaces.current().executeScript("PF('delete_anticipo_dialog').hide()");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Anticipo Eliminado"));
        
    }
    
    // FormatoFecha da el formato a la fecha que se presentara en la tabla.
    public static String FormatoFecha(Date fecha) {
        return new SimpleDateFormat("dd-MM-yyyy").format(fecha);
    }
    
    
}
