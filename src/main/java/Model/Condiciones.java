
package Model;


public class Condiciones {
     int idCondiciones;
     Double descuento;
     int diasNeto;
     int diasDescuento;
     int cantDiasVencidos;
     String descripcion;
     Proveedor  proveedor ;

     public Condiciones(int idCondiciones, Double descuento, int diasNeto, int diasDescuento, int cantDiasVencidos, String descripcion, Proveedor proveedor) {
          this.idCondiciones = idCondiciones;
          this.descuento = descuento;
          this.diasNeto = diasNeto;
          this.diasDescuento = diasDescuento;
          this.cantDiasVencidos = cantDiasVencidos;
          this.descripcion = descripcion;
          this.proveedor = proveedor;
     }

     
     public Condiciones() {
     }

     public int getIdCondiciones() {
          return idCondiciones;
     }

     public void setIdCondiciones(int idCondiciones) {
          this.idCondiciones = idCondiciones;
     }

     public Double getDescuento() {
          return descuento;
     }

     public void setDescuento(Double descuento) {
          this.descuento = descuento;
     }

     public int getDiasNeto() {
          return diasNeto;
     }

     public void setDiasNeto(int diasNeto) {
          this.diasNeto = diasNeto;
     }

     public int getDiasDescuento() {
          return diasDescuento;
     }

     public void setDiasDescuento(int diasDescuento) {
          this.diasDescuento = diasDescuento;
     }

     public int getCantDiasVencidos() {
          return cantDiasVencidos;
     }

     public void setCantDiasVencidos(int cantDiasVencidos) {
          this.cantDiasVencidos = cantDiasVencidos;
     }

     public String getDescripcion() {
          return descripcion;
     }

     public void setDescripcion(String descripcion) {
          this.descripcion = descripcion;
     }

     public Proveedor getProveedor() {
          return proveedor;
     }

     public void setProveedor(Proveedor proveedor) {
          this.proveedor = proveedor;
     }

   
     
}
