/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Anticipo;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author elect
 */

@ManagedBean(name = "anticipoMB")
@RequestScoped
public class AnticipoMB {

    private List<Anticipo> anticipos;
    
    public AnticipoMB() {
        this.anticipos = Anticipo.getAll(); // trae solo los datos de los anticipos
        Anticipo.GetAllDBProveedor(this.anticipos); // trae los proveedores de cada anticipo.
    }

    public List<Anticipo> getAnticipos() {
        return anticipos;
    }

    public void setAnticipos(List<Anticipo> anticipos) {
        this.anticipos = anticipos;
    }
    
    
    
}
