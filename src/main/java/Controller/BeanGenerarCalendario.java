/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataView.FacturaDAO;
import Model.Factura;
import Model.ManagerCalendario;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.PrimeFaces;

/**
 *
 * @author elect
 */
//@Named(value = "bindGenerarCalendario")
@ManagedBean(name = "beanGenerarCalendario")
@RequestScoped
public class BeanGenerarCalendario {

    // Parametro de la vista
    private LocalDate desde;
    private LocalDate hasta;
    private boolean disabled_fecha;
    private String tipo;
    
    private FacturaDAO facturaDAO;
    List<Factura> facturas; // datos que se muestran en la tabla.

    // Datos a consultar en la db
    private ManagerCalendario managerCalendario;
    
    

    public BeanGenerarCalendario()  {
        
    }
    
    @PostConstruct
    public void init() {
        managerCalendario = new ManagerCalendario();
        
        this.desde = LocalDate.now().withMonth(1).withDayOfMonth(1);
        this.hasta = LocalDate.now().withMonth(12).withDayOfMonth(31);
        this.tipo = "2";
        
        //facturaDAO = new FacturaDAO();
        //facturas = facturaDAO.llenar();
        this.facturas = Factura.get_fac_pro(this.desde, this.hasta, Integer.parseInt(this.tipo));
        
        this.disabled_fecha = true;
    }

    ////////////////////////////////
    // GET AND SET DE LOS METODOS //
    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public FacturaDAO getFacturaDAO() {
        return facturaDAO;
    }

    public void setFacturaDAO(FacturaDAO facturaDAO) {
        this.facturaDAO = facturaDAO;
    }
    
    public ManagerCalendario getManagerCalendario() {
        return managerCalendario;
    }

    public void setManagerCalendario(ManagerCalendario managerCalendario) {
        this.managerCalendario = managerCalendario;
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

    public boolean isDisabled_fecha() {
        return disabled_fecha;
    }

    public void setDisabled_fecha(boolean disabled_fecha) {
        this.disabled_fecha = disabled_fecha;
    }

    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

   
    

    // METODOS PARA TRABAJAR CON LOS DATOS.
    ///////////////////////////////////////
    // Metodo funcional para exportar pdf
    public void exportpdf() throws IOException, JRException {

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        // Cabecera de la respuesta.
        ec.responseReset();
        ec.setResponseContentType("application/pdf");
        ec.setResponseHeader("Content-disposition", "attachment; filename=Reporte.pdf");

        // tomamos el stream para llenarlo con el pdf.
        try (OutputStream stream = ec.getResponseOutputStream()) {
            ManagerCalendario mc = new ManagerCalendario();

            // Parametros para el reporte.
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("titulo", "Reporte desde java");

            // leemos la plantilla para el reporte.
            File filetext = new File(FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getRealPath("/PlantillasReportes/Calendario.jasper"));

            // llenamos la plantilla con los datos.
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    filetext.getPath(),
                    parametros,
                    new JRBeanCollectionDataSource(mc.getListFacturas())
            );

            // exportamos a pdf.
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            //JasperExportManager.exportReportToXmlStream(jasperPrint, outputStream);

            stream.flush();
            stream.close();
        }

        // enviamos la respuesta.
        fc.responseComplete();

        System.out.println("fin proccess");
    }
    
    public float Total() {
        final float totalPorPagar = 0;
        
        
        return totalPorPagar;
    }
    
    public void generar() {
        System.out.println(this.desde);
        System.out.println(this.hasta);
        System.out.println(this.tipo);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Consultado los datos."));
        System.out.println(this.facturas);
        
        this.facturas = Factura.get_fac_pro(this.desde, this.hasta, Integer.parseInt(this.tipo));
        PrimeFaces.current().ajax().update(":form:tablafacturas");
    }
    
    public void on_cambio() {
        if ("1".equals(this.tipo)) {
            this.disabled_fecha = false;
        } else {
            this.disabled_fecha = true;
        }
    }
}
