
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SalidasDao extends Conexion implements Dao{
    
    private ModeloSalidas getSalida(Object o ){
        ModeloSalidas salida;
        try {
           salida = (ModeloSalidas) o;
        } catch (Exception e) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.WARNING, null, e);
            return null;
        } 
        return salida;
    }

    @Override
    public Object guardar(Object o) {
        
        PreparedStatement ps = null;
        Connection con = getConexion();
        ModeloSalidas salida = getSalida(o);
        String sql = "INSERT INTO salidas (fecha, cantidad, producto) VALUES (?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, salida.getFecha());           
            ps.setDouble(2, salida.getCantidad());
            ps.setString(3, salida.getProducto());            
            
            ps.execute();
            return true;
            
        } catch(SQLException ex){
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
           return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public Object modificar(Object o) {
        
        PreparedStatement ps = null;
        Connection con = getConexion();
        ModeloSalidas salida = getSalida(o);
        String sql = "UPDATE salidas SET fecha=?, cantidad=?, producto=? WHERE idsalidas=? ";
        
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, salida.getFecha());          
            ps.setDouble(2, salida.getCantidad());
            ps.setString(3, salida.getProducto());            
            ps.setInt(4, salida.getIdsalidas());
            
            ps.execute();
            return true;
            
        } catch(SQLException ex){
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
           return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public Object eliminar(Object o) {
        
        PreparedStatement ps = null;       
        Connection con = getConexion();
        ModeloSalidas salida = getSalida(o);
            //Sentencia para eliminar el producto
        String sql = "DELETE FROM salidas WHERE idsalidas=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, salida.getIdsalidas());
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

    @Override
    public Object buscar(Object o) {
        
        PreparedStatement ps = null;     
        ResultSet rs = null;
        Connection con = getConexion();
        ModeloSalidas salida = getSalida(o);
            //Sentencia para buscar
        String sql = "SELECT * FROM salidas WHERE fecha=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, salida.getFecha());
            rs = ps.executeQuery();
           
            if(rs.next()){
                salida.setIdsalidas(Integer.parseInt(rs.getString("idsalidas")));
                salida.setFecha(rs.getString("fecha"));               
                salida.setCantidad(Double.parseDouble(rs.getString("cantidad")));
                salida.setProducto(rs.getString("producto"));
                
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
    
    /*
    
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
    }*/
}
