/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompanny.interfaces_mcp;

import Model.ManagerCalendario;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


/**
 *
 * @author elect
 */
//@Named(value = "bindGenerarCalendario")
@ManagedBean(name = "bindGenerarCalendario")
@RequestScoped
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
    
    public void mostrar() throws IOException, JRException {
        System.out.println("Mostrar");
        System.out.println(this.proveedor);
        System.out.println(this.todosProveedores);
        System.out.println(this.desde);
        System.out.println(this.hasta);
        System.out.println(this.sinfecha);
    
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
       
        response.setContentType("application/pdf");
        
        response.addHeader("Content-disposition", "attachment; filename=jsfReport.pdf");
        
        ManagerCalendario mc = new ManagerCalendario();
        
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("titulo", "Reporte desde java");
        
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            File filetext = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Blank_A4.jasper"));
            System.out.println("Se puede leer: " + filetext.canRead());
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    filetext.getPath(), 
                    parametros,
                    new JRBeanCollectionDataSource(mc.getListCuotas())
            );
            
            System.out.println("Ancho: " + jasperPrint.getPageWidth());
           
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            //JasperExportManager.exportReportToXmlStream(jasperPrint, outputStream);
            
            outputStream.flush();
            outputStream.close();
           
        }
        
        FacesContext.getCurrentInstance().responseComplete();
        
        System.out.println("fin proccess");
    }
    
    public void mostrar2() throws IOException, JRException { 
        
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        
        ec.responseReset();
        ec.setResponseContentType("application/pdf");
        ec.setResponseHeader("Content-disposition", "attachment; filename=hola.pdf");
        
        try (OutputStream stream = ec.getResponseOutputStream()) {
            ManagerCalendario mc = new ManagerCalendario();
            
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("titulo", "Reporte desde java");
            
            
            File filetext = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Blank_A4.jasper"));
            
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    filetext.getPath(),
                    parametros,
                    new JRBeanCollectionDataSource(mc.getListCuotas())
            );
            
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            //JasperExportManager.exportReportToXmlStream(jasperPrint, outputStream);
            
            stream.flush();
            stream.close();
        }

        
        fc.responseComplete();
        
        System.out.println("fin proccess");
    }
    
    @PostConstruct
    public void init() {
        
        
    }
    
    public void exportarpdf(ActionEvent actionEvent) throws JRException, IOException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("titulo", "Reporte desde java");
        ManagerCalendario mc = new ManagerCalendario();
        
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Blank_A4.jasper"));
        System.out.println("path: " + jasper.getPath());
        JasperPrint jasperfile = 
                JasperFillManager.fillReport(
                        jasper.getPath(), 
                        parametros, 
                        new JRBeanCollectionDataSource(mc.getListCuotas()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-Disposition", "attachment; filename=jsfReport.pdf");
        
        ServletOutputStream stream = response.getOutputStream();
        
        JasperExportManager.exportReportToPdfStream(jasperfile, stream);
        
        stream.flush();
        stream.close();
        
        FacesContext.getCurrentInstance().responseComplete();
    }
    
}
