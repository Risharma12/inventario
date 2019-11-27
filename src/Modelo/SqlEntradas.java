
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SqlEntradas extends Conexion {
    
    public boolean guardarentrada (ModeloEntradas guardar) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO entradas (fecha, comentarios, cantidad, producto, proveedor) VALUES (?, ?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, guardar.getFecha());
            ps.setString(2, guardar.getComentarios());
            ps.setDouble(3, guardar.getCantidad());
            ps.setString(4, guardar.getProducto());
            ps.setString(5, guardar.getProveedor());
            
            ps.execute();
            return true;
            
        } catch(SQLException ex){
            Logger.getLogger(Sql_Registro_Producto.class.getName()).log(Level.SEVERE, null, ex);
           return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean modificarentrada (ModeloEntradas modificar) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE entradas SET fecha=?, comentarios=?, cantidad=?, producto=?, proveedor=? WHERE identradas=? ";
        
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, modificar.getFecha());
            ps.setString(2, modificar.getComentarios());
            ps.setDouble(3, modificar.getCantidad());
            ps.setString(4, modificar.getProducto());
            ps.setString(5, modificar.getProveedor());
            ps.setInt(6, modificar.getIdentradas());
            
            ps.execute();
            return true;
            
        } catch(SQLException ex){
            Logger.getLogger(Sql_Registro_Producto.class.getName()).log(Level.SEVERE, null, ex);
           return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean eliminarentrada(ModeloEntradas eliminar) {
        PreparedStatement ps = null;       
        Connection con = getConexion();
        
            //Sentencia para eliminar el producto
        String sql = "DELETE FROM entradas WHERE identradas=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, eliminar.getIdentradas());
            ps.execute();         
                     
            
               return true;
                      
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean buscarentrada(ModeloEntradas buscar) {
        PreparedStatement ps = null;     
        ResultSet rs = null;
        Connection con = getConexion();
        
            //Sentencia para buscar
        String sql = "SELECT * FROM entradas WHERE fecha=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, buscar.getFecha());
            rs = ps.executeQuery();
           
            if(rs.next()){
                buscar.setIdentradas(Integer.parseInt(rs.getString("identradas")));
                buscar.setFecha(rs.getString("fecha"));
                buscar.setComentarios(rs.getString("comentarios"));
                buscar.setCantidad(Double.parseDouble(rs.getString("cantidad")));
                buscar.setProducto(rs.getString("producto"));
                buscar.setProveedor(rs.getString("proveedor"));
                return true;
            }                   
            
               return false;
                      
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
