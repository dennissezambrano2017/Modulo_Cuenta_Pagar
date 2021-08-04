/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ebert
 */
public class Proveedor {

     int idProveedor;
     String codigo;
     String razonSocial;
     String ruc;
     String nombre;
     String direccion;
     String email;
     String webPage;
     String contacto;
     String telefono;
     boolean estado;

     Double descuento;
     int diasNeto;
     int diasDescuento;
     int cantDiasVencidos;
     String descripcion;

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

     public Proveedor(String codigo, String nombre, String email, String telefono, String contacto, boolean estado) {
          this.codigo = codigo;
          this.nombre = nombre;
          this.email = email;
          this.contacto = contacto;
          this.telefono = telefono;
          this.estado = estado;
     }

     public Proveedor(int idProveedor, String codigo, String razonSocial, String ruc, String nombre, String direccion, String email, String webPage, String contacto, String telefono, boolean estado) {
          this.idProveedor = idProveedor;
          this.codigo = codigo;
          this.razonSocial = razonSocial;
          this.ruc = ruc;
          this.nombre = nombre;
          this.direccion = direccion;
          this.email = email;
          this.webPage = webPage;
          this.contacto = contacto;
          this.telefono = telefono;
          this.estado = estado;
     }

     public Proveedor(int idProveedor) {
          this.idProveedor = idProveedor;
     }

     public Proveedor() {
     }

     public int getIdProveedor() {
          return idProveedor;
     }

     public void setIdProveedor(int idProveedor) {
          this.idProveedor = idProveedor;
     }

     public String getCodigo() {
          return codigo;
     }

     public void setCodigo(String codigo) {
          this.codigo = codigo;
     }

     public String getRazonSocial() {
          return razonSocial;
     }

     public void setRazonSocial(String razonSocial) {
          this.razonSocial = razonSocial;
     }

     public String getRuc() {
          return ruc;
     }

     public void setRuc(String ruc) {
          this.ruc = ruc;
     }

     public String getNombre() {
          return nombre;
     }

     public void setNombre(String nombre) {
          this.nombre = nombre;
     }

     public String getDireccion() {
          return direccion;
     }

     public void setDireccion(String direccion) {
          this.direccion = direccion;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public String getWebPage() {
          return webPage;
     }

     public void setWebPage(String webPage) {
          this.webPage = webPage;
     }

     public String getContacto() {
          return contacto;
     }

     public void setContacto(String contacto) {
          this.contacto = contacto;
     }

     public String getTelefono() {
          return telefono;
     }

     public void setTelefono(String telefono) {
          this.telefono = telefono;
     }

     public boolean isEstado() {
          return estado;
     }

     public void setEstado(boolean estado) {
          this.estado = estado;
     }

     @Override
     public int hashCode() {
          int hash = 5;
          hash = 67 * hash + this.idProveedor;
          return hash;
     }

     @Override
     public boolean equals(Object obj) {
          if (this == obj) {
               return true;
          }
          if (obj == null) {
               return false;
          }
          if (getClass() != obj.getClass()) {
               return false;
          }
          final Proveedor other = (Proveedor) obj;
          if (this.idProveedor != other.idProveedor) {
               return false;
          }
          return true;
     }

}
