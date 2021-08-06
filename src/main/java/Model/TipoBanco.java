/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author PAOLA
 */
public class TipoBanco {
    private int idBanco;
    private String Descrpcion;

    public TipoBanco() {
    }

    public TipoBanco(int idBanco, String Descrpcion) {
        this.idBanco = idBanco;
        this.Descrpcion = Descrpcion;
    }

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public String getDescrpcion() {
        return Descrpcion;
    }

    public void setDescrpcion(String Descrpcion) {
        this.Descrpcion = Descrpcion;
    }
    
}
