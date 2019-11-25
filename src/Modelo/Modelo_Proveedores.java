
package Modelo;


public class Modelo_Proveedores {
    
    //Variables de los campos de la tabla de proveedores
    private int id_proveedor;
    private String nombre_proveedor;
    private String direccion_proveedor;
    private String telefono_proveedor;
    private String rfc_proveedor;
    private String ciudad_proveedor;
    
    
    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getDireccion_proveedor() {
        return direccion_proveedor;
    }

    public void setDireccion_proveedor(String direccion_proveedor) {
        this.direccion_proveedor = direccion_proveedor;
    }

    public String getTelefono_proveedor() {
        return telefono_proveedor;
    }

    public void setTelefono_proveedor(String telefono_proveedor) {
        this.telefono_proveedor = telefono_proveedor;
    }

    public String getRfc_proveedor() {
        return rfc_proveedor;
    }

    public void setRfc_proveedor(String rfc_proveedor) {
        this.rfc_proveedor = rfc_proveedor;
    }

    public String getCiudad_proveedor() {
        return ciudad_proveedor;
    }

    public void setCiudad_proveedor(String ciudad_proveedor) {
        this.ciudad_proveedor = ciudad_proveedor;
    }
}
