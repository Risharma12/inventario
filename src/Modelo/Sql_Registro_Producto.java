
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Sql_Registro_Producto extends Conexion {
    
    
    public boolean guardarproducto(Modelo_Producto guardar_producto)
   {
   
     PreparedStatement ps = null;
     Connection con = getConexion();
            //Senetencia para insertar datos en la tabla de productos
       String sql = "INSERT INTO productos (codigo, nombre, descripcion, precio, tipo) VALUES(?, ?, ?, ?, ?)";
      
       
       try {
           ps = con.prepareStatement(sql);      
           
           ps.setString(1,guardar_producto.getCodigo());           
           ps.setString(2,guardar_producto.getNombre());           
           ps.setString(3,guardar_producto.getDescripcion());           
           ps.setDouble(4,guardar_producto.getPrecio());     
           ps.setString(5,guardar_producto.getTipo());
          
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
    
    
       public boolean modificarproducto(Modelo_Producto modificar_producto) {
        PreparedStatement ps = null;
        Connection con = getConexion();
            //Sentencia para actualizar datos de la tabla productos en la base de datos
        String sql = "UPDATE productos SET codigo=?, nombre=?, descripcion=?, precio=?, tipo=? WHERE idProductos=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, modificar_producto.getCodigo());
            ps.setString(2, modificar_producto.getNombre());
            ps.setString(3, modificar_producto.getDescripcion());
            ps.setDouble(4, modificar_producto.getPrecio());
            ps.setString(5, modificar_producto.getTipo());
            ps.setInt(6, modificar_producto.getIdProductos());
            
            
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
     
       
       
        public boolean eliminarproducto(Modelo_Producto eliminar_producto) {
        PreparedStatement ps = null;       
        Connection con = getConexion();
        
            //Sentencia para eliminar el producto
        String sql = "DELETE FROM productos WHERE idProductos=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, eliminar_producto.getIdProductos());
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
        
    public boolean buscarproducto(Modelo_Producto buscar_producto) {
        PreparedStatement ps = null;     
        ResultSet rs = null;
        Connection con = getConexion();
        
            //Sentencia para eliminar el producto
        String sql = "SELECT * FROM productos WHERE codigo=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, buscar_producto.getCodigo());
            rs = ps.executeQuery();
           
            if(rs.next()){
                buscar_producto.setIdProductos(Integer.parseInt(rs.getString("idProductos")));
                buscar_producto.setCodigo(rs.getString("codigo"));
                buscar_producto.setNombre(rs.getString("nombre"));
                buscar_producto.setDescripcion(rs.getString("descripcion"));
                buscar_producto.setPrecio(Double.parseDouble(rs.getString("precio")));
                buscar_producto.setTipo(rs.getString("tipo"));
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
