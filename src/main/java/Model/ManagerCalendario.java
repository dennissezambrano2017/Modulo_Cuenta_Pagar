/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elect
 */
public class ManagerCalendario {

    
    private String Titulo;
    private LocalDate Fecha; // fecha del corte, que se genero el reporte
    // lista de las factuas a los cuales hay que pagar.
    private List<ReporteFactura> listFacturas;

    
    public ManagerCalendario() {
        //this.listCuotas = new ArrayList<Cuotas>();
        this.listFacturas = new ArrayList<>();
        this.listFacturas.add(
                new ReporteFactura(
                        LocalDate.now(), 
                        "software sa", 
                        "001", 
                        100, 
                        LocalDate.now().plusWeeks(1), "Contado"));
        this.listFacturas.add(
                new ReporteFactura(
                        LocalDate.now(), 
                        "Coca cola", 
                        "002", 
                        500, 
                        LocalDate.now().plusWeeks(1), "Cuotas"));
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public LocalDate getFecha() {
        return Fecha;
    }

    public void setFecha(LocalDate Fecha) {
        this.Fecha = Fecha;
    }

    public List<ReporteFactura> getListFacturas() {
        return listFacturas;
    }

    public void setListFacturas(List<ReporteFactura> listFacturas) {
        this.listFacturas = listFacturas;
    }
    
    // getListFacturasFilter retorna las facturas que este dentro del rango
    // de las fechas que se le pasa
    public List<ReporteFactura> getListFacturasFilter(LocalDate inicio, LocalDate fin, boolean sinFecha) {
        System.out.println("lista de factura con filtro");
        List<ReporteFactura> resultado; // Lista donde se almacena las factura que pasan los parametros.
        resultado = new ArrayList<>();
        listFacturas.forEach(fac -> {
            // Condicionamos si es con fecha, o sin los para metros de fechas.
            if (sinFecha) {
                resultado.add(fac);
            }else {
                if (fac.getFechaVencimiento().isAfter(inicio) && fac.getFechaVencimiento().isBefore(fin)) {
                    resultado.add(fac);
                }
            }
            
        });
        
        resultado.add(new ReporteFactura(LocalDate.now(), "ultimo", "003", 22, LocalDate.now().plusDays(2), "Contable"));
        return resultado;
    }
    
    
}
