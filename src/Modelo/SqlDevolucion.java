
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SqlDevolucion extends Conexion {
        public boolean guardardevolucion(ModeloDevolucion guardar)
   {
   
     PreparedStatement ps = null;
     Connection con = getConexion();
            //Senetencia para insertar datos en la tabla de productos
       String sql = "INSERT INTO devoluciones (fecha, comentarios, cantidad, producto, proveedor) VALUES(?, ?, ?, ?, ?)";
      
       
       try {
           ps = con.prepareStatement(sql);      
           
           ps.setString(1,guardar.getFecha());           
           ps.setString(2,guardar.getComentarios());           
           ps.setDouble(3,guardar.getCantidad());           
           ps.setString(4,guardar.getProducto());     
           ps.setString(5,guardar.getProveedor());
          
           ps.execute();
           return true;
          
                
           
           
       } catch (SQLException ex) {
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
    
    
       public boolean modificardevolucion(ModeloDevolucion modificar) {
        PreparedStatement ps = null;
        Connection con = getConexion();
            //Sentencia para actualizar datos de la tabla productos en la base de datos
        String sql = "UPDATE devoluciones SET fecha=?, comentarios=?, cantidad=?, producto=?, proveedor=? WHERE iddevoluciones=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, modificar.getFecha());
            ps.setString(2, modificar.getComentarios());
            ps.setDouble(3, modificar.getCantidad());
            ps.setString(4, modificar.getProducto());
            ps.setString(5, modificar.getProveedor());
            ps.setInt(6, modificar.getIddevoluciones());
            
            
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
     
       
       
        public boolean eliminardevolucion(ModeloDevolucion eliminar) {
        PreparedStatement ps = null;       
        Connection con = getConexion();
        
            //Sentencia para eliminar el producto
        String sql = "DELETE FROM devoluciones WHERE iddevoluciones=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, eliminar.getIddevoluciones());
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
        
    public boolean buscardevolucion(ModeloDevolucion buscar) {
        PreparedStatement ps = null;     
        ResultSet rs = null;
        Connection con = getConexion();
        
            //Sentencia para buscar
        String sql = "SELECT * FROM devoluciones WHERE fecha=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, buscar.getFecha());
            rs = ps.executeQuery();
           
            if(rs.next()){
                buscar.setIddevoluciones(Integer.parseInt(rs.getString("iddevoluciones")));
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
