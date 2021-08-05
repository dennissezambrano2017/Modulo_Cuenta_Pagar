/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.time.LocalDate;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author elect
 */

@ManagedBean(name = "estadoDeCuentaMB")
@RequestScoped
public class EstadoDeCuentaMB {

    /**
     * Creates a new instance of EstadoDeCuentaMB
     */
    
    private LocalDate desde;
    
    public EstadoDeCuentaMB() {
        desde = LocalDate.now();
    }
    
    public LocalDate getDesde() {
        return desde;
    }

    public void setDesde(LocalDate desde) {
        this.desde = desde;
    }

    
}
