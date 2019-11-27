
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SqlSalidas extends Conexion {
    
    public boolean guardarsalida (ModeloSalidas guardar) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO salidas (fecha, cantidad, producto) VALUES (?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, guardar.getFecha());           
            ps.setDouble(2, guardar.getCantidad());
            ps.setString(3, guardar.getProducto());            
            
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
    
    public boolean modificarsalida (ModeloSalidas modificar) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE salidas SET fecha=?, cantidad=?, producto=? WHERE idsalidas=? ";
        
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, modificar.getFecha());          
            ps.setDouble(2, modificar.getCantidad());
            ps.setString(3, modificar.getProducto());            
            ps.setInt(4, modificar.getIdsalidas());
            
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
    
    public boolean eliminarsalida(ModeloSalidas eliminar) {
        PreparedStatement ps = null;       
        Connection con = getConexion();
        
            //Sentencia para eliminar el producto
        String sql = "DELETE FROM salidas WHERE idsalidas=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, eliminar.getIdsalidas());
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
    
    public boolean buscarsalida(ModeloSalidas buscar) {
        PreparedStatement ps = null;     
        ResultSet rs = null;
        Connection con = getConexion();
        
            //Sentencia para buscar
        String sql = "SELECT * FROM salidas WHERE fecha=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, buscar.getFecha());
            rs = ps.executeQuery();
           
            if(rs.next()){
                buscar.setIdsalidas(Integer.parseInt(rs.getString("idsalidas")));
                buscar.setFecha(rs.getString("fecha"));               
                buscar.setCantidad(Double.parseDouble(rs.getString("cantidad")));
                buscar.setProducto(rs.getString("producto"));
                
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
