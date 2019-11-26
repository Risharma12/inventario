
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;


public class ModeloEntradas {
    private int identradas;
    private String nombre;
    private String nombreProv;
    private String fecha;
    private String comentarios;
    private Double cantidad;
    private int prod_ent;
    private int prov_ent;

    public int getIdentradas() {
        return identradas;
    }

    public void setIdentradas(int identradas) {
        this.identradas = identradas;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre (String nombre) {
        this.nombre = nombre;
    }

    public String getNombreProv() {
        return nombreProv;
    }

    public void setNombreProv(String nombreProv) {
        this.nombreProv = nombreProv;
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

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public int getProd_ent() {
        return prod_ent;
    }

    public void setProd_ent(int prod_ent) {
        this.prod_ent = prod_ent;
    }

    public int getProv_ent() {
        return prov_ent;
    }

    public void setProv_ent(int prov_ent) {
        this.prov_ent = prov_ent;
    }
    
   
    public String toString() {
        return this.nombre;
        
    }
    
    
    public Vector<ModeloEntradas> mostrarProductos() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        
        Vector<ModeloEntradas> datos = new Vector<ModeloEntradas>();
        ModeloEntradas dat = null;
        
        try{
            
            String sql = "SELECT * FROM productos";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            dat = new ModeloEntradas();
            dat.setIdentradas(0);
            dat.setNombre("Seleccione");
            datos.add(dat);
            
            
            while(rs.next()){
                dat = new ModeloEntradas();
                dat.setProd_ent(rs.getInt("idProductos"));
                dat.setNombre(rs.getString("nombre"));
                datos.add(dat);
            }
            rs.close();
            
        } catch(SQLException ex){
            System.err.println(ex.toString());
        } 
        return datos;
    }
    
    public Vector<ModeloEntradas> mostrarProveedores() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        
        Vector<ModeloEntradas> datos = new Vector<ModeloEntradas>();
        ModeloEntradas dat = null;
        
        try{
            
            String sql = "SELECT * FROM proveedores";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            dat = new ModeloEntradas();
            dat.setIdentradas(0);
            dat.setNombre("Seleccione");
            datos.add(dat);
            
            
            while(rs.next()){
                dat = new ModeloEntradas();
                dat.setIdentradas(rs.getInt("idproveedores"));
                dat.setNombre(rs.getString("nombre"));
                datos.add(dat);
            }
            rs.close();
            
        } catch(SQLException ex){
            System.err.println(ex.toString());
        } 
        return datos;
    }
}
