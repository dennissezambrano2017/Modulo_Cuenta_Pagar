/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ManagerCalendario;
import Model.ReporteFactura;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author elect
 */
//@Named(value = "bindGenerarCalendario")
@ManagedBean(name = "beanGenerarCalendario")
@RequestScoped
public class BeanGenerarCalendario implements Serializable {

    // Parametro de la vista
    private LocalDate desde = LocalDate.now();
    private LocalDate hasta = LocalDate.now().plusWeeks(1);
    private boolean sinfecha;

    // Datos a consultar en la db
    private ManagerCalendario managerCalendario;
    
    

    public BeanGenerarCalendario()  {
        managerCalendario = new ManagerCalendario();
        this.desde = LocalDate.now().withMonth(1).withDayOfMonth(1);
        this.hasta = LocalDate.now().withMonth(12).withDayOfMonth(31);
        
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

    public boolean isSinfecha() {
        return sinfecha;
    }

    public void setSinfecha(boolean sinfecha) {
        this.sinfecha = sinfecha;
    }

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
    
}
