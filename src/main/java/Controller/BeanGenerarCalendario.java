/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataView.FacturaDAO;
import Model.Factura;
import Model.ManagerCalendario;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
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
import javax.faces.view.ViewScoped;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author elect
 */
//@Named(value = "bindGenerarCalendario")
@ManagedBean(name = "beanGenerarCalendario")
@RequestScoped
public class BeanGenerarCalendario  {

    // Parametro de la vista
    private LocalDate desde;
    private LocalDate hasta;
    private boolean disabled_fecha;
    private String tipo;
    
    private FacturaDAO facturaDAO;
    List<Factura> facturas; // datos que se muestran en la tabla.

    private StreamedContent file;
    
    

    public BeanGenerarCalendario()  {
        
    }
    
    @PostConstruct
    public void init() {
        
        
        this.desde = LocalDate.now().withMonth(1).withDayOfMonth(1);
        this.hasta = LocalDate.now().withMonth(12).withDayOfMonth(31);
        this.tipo = "2";
        
        //facturaDAO = new FacturaDAO();
        //facturas = facturaDAO.llenar();
        this.facturas = FacturaDAO.get_fac_pro(this.desde, this.hasta, Integer.parseInt(this.tipo));
        
        this.disabled_fecha = true;
        
        // confi of file
        //this.file = DefaultStreamedContent.builder().name("reporte.pdf").contentType("aplication/pdf").stream(() -> null).build();
        //exportar2();
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

    public StreamedContent getFile() {
        System.out.println("///getFiledata");
        this.facturas.forEach(fac -> {
                System.out.println(fac.getProveedor().getNombre());
                System.out.println(fac.getFecha());
            });
        System.out.println("///getFiledata fin");
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

   
    

    // METODOS PARA TRABAJAR CON LOS DATOS.
    ///////////////////////////////////////
    // Metodo funcional para exportar pdf
    public void exportpdf() throws IOException, JRException {
        System.out.println("metodo Export");
        System.out.println(this.desde);
        System.out.println(this.hasta);
        System.out.println(this.tipo);
        this.facturas = FacturaDAO.get_fac_pro(this.desde, this.hasta, Integer.parseInt(this.tipo));
        
        System.out.println("Datos que se ingresan al reporte, metodo exportpdf");
        this.facturas.forEach(fac -> {
            System.out.println(fac.getProveedor().getNombre());
            System.out.println(fac.getFecha());
        });
        System.out.println("Datos que se ingresan al reporte, metodo exportpdf fin");
        
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
            parametros.put("fecha", LocalDate.now().toString());

            // leemos la plantilla para el reporte.
            File filetext = new File(FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getRealPath("/PlantillasReportes/practica_reporte.jasper"));

            // llenamos la plantilla con los datos.
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    filetext.getPath(),
                    parametros,
                    new JRBeanCollectionDataSource(this.facturas)
            );

            // exportamos a pdf.
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            //JasperExportManager.exportReportToXmlStream(jasperPrint, outputStream);

            stream.flush();
            stream.close();
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            // enviamos la respuesta.
            fc.responseComplete();

            System.out.println("fin proccess");
        }

        
    }
    
    public void exportar2() {
        // Parametros para el reporte.
        //this.facturas = Factura.get_fac_pro(this.desde, this.hasta, Integer.parseInt(this.tipo));
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("titulo", "Reporte desde java");
        parametros.put("fecha", LocalDate.now().toString());
        
        try {
        
        
            File filetext = new File(FacesContext
                       .getCurrentInstance()
                       .getExternalContext()
                       .getRealPath("/PlantillasReportes/practica_reporte.jasper"));

            System.out.println("Datos que se ingresan al reporte");
            this.facturas.forEach(fac -> {
                System.out.println(fac.getProveedor().getNombre());
                System.out.println(fac.getFecha());
            });
            
            // llenamos la plantilla con los datos.
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    filetext.getPath(),
                    parametros,
                    new JRBeanCollectionDataSource(this.facturas)
            );

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            
            // exportamos a pdf.
            JasperExportManager.exportReportToPdfStream(jasperPrint, os);
            os.flush();
            os.close();
            
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            
            this.file = DefaultStreamedContent.builder().name("reportedeexpor.pdf").contentType("application/pdf").stream(() -> is).build();
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println("Fin, proceso de exportar 2");
    }
    
    public float Total() {
        final float totalPorPagar = 0;
        
        
        return totalPorPagar;
    }
    
    public void generar() {
        System.out.println(this.desde);
        System.out.println(this.hasta);
        System.out.println(this.tipo);
        
        if (!this.desde.isBefore(this.hasta)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fechas mal selecionadas"));
            return;
        }
        // Tremos los datos desde la base de datos
        this.facturas = FacturaDAO.get_fac_pro(this.desde, this.hasta, Integer.parseInt(this.tipo));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Operación exitosa"));
        // Actulizamos la tabla, para ver los resultados.
        PrimeFaces.current().ajax().update(":form:tablafacturas");
        
        // Antualizamos los datoa para el reporte
        //exportar2();
    }
    
    public void on_cambio() {
        this.disabled_fecha = !"1".equals(this.tipo);
    }
}
