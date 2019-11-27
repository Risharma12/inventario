
package Modelo;


public class ModeloDevolucion {
    private int iddevoluciones;
    private String fecha, comentarios, producto, proveedor;
    private Double cantidad;

    public int getIddevoluciones() {
        return iddevoluciones;
    }

    public void setIddevoluciones(int iddevoluciones) {
        this.iddevoluciones = iddevoluciones;
    }    

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
