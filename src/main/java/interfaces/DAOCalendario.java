/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Model.ManagerCalendario;
import Model.ReporteFactura;
import java.util.List;

/**
 *
 * @author elect
 */
public interface DAOCalendario {
    public ManagerCalendario Selecionar() throws Exception;
    public List<ReporteFactura> listaFactura() throws Exception;
}
