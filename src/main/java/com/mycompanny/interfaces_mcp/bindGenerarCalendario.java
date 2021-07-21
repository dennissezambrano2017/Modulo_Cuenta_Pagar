/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompanny.interfaces_mcp;

import java.time.LocalDate;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author elect
 */
//@Named(value = "bindGenerarCalendario")
@ManagedBean(name = "bindGenerarCalendario")
@ViewScoped
public class bindGenerarCalendario implements Serializable {

    private String proveedor;
    private boolean  todosProveedores;
    
    private LocalDate desde = LocalDate.now();
    private LocalDate hasta = LocalDate.now();
    
    private boolean sinfecha;

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public boolean isTodosProveedores() {
        return todosProveedores;
    }

    public void setTodosProveedores(boolean todosProveedores) {
        this.todosProveedores = todosProveedores;
    }

    public LocalDate getDesde() {
        return desde;
    }

    public void setDesde(LocalDate desde) {
        this.desde = desde;
    }

    public LocalDate getHasta() {
        return hasta;
    }

    public void setHasta(LocalDate hasta) {
        this.hasta = hasta;
    }

    public boolean isSinfecha() {
        return sinfecha;
    }

    public void setSinfecha(boolean sinfecha) {
        this.sinfecha = sinfecha;
    }

   
    
    public bindGenerarCalendario() {
        System.out.println("Iniciamos la class");
    }
    
    public void mostrar() {
        System.out.println("Mostrar");
        System.out.println(this.proveedor);
        System.out.println(this.todosProveedores);
        System.out.println(this.desde);
        System.out.println(this.hasta);
        System.out.println(this.sinfecha);
        
    }
    
    @PostConstruct
    public void init() {
        
        
    }
    
}
