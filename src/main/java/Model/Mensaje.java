package Model;

/**
 *
 * @author elect
 * 
 * Esta estructura de mensaje se la estara ultizando para enviar info al front
 */
public class Mensaje {
    private String tipo;
    private String data;

    public Mensaje() {
    }


    
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
}
