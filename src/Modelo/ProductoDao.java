package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProductoDao extends Conexion implements Dao {
    
    private Modelo_Producto getProducto(Object o ){
        Modelo_Producto producto;
        try {
            producto = (Modelo_Producto) o;
        } catch (Exception e) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.WARNING, null, e);
            return null;
        } 
        return producto;
    }

    @Override
    public Object guardar(Object o) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        Modelo_Producto producto = getProducto(o);

        //Senetencia para insertar datos en la tabla de productos
        String sql = "INSERT INTO productos (codigo, nombre, descripcion, precio, tipo) VALUES(?, ?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setDouble(4, producto.getPrecio());
            ps.setString(5, producto.getTipo());

            ps.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                con.close();
                
            } catch (SQLException e) {
                System.err.println(e);
            }
            try {
                if(ps != null){
                    ps.close();
                }
                
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public Object modificar(Object o) {
        PreparedStatement ps = null;
        Connection con = getConexion();
       Modelo_Producto producto = getProducto(o);
            
        String sql = "UPDATE productos SET codigo=?, nombre=?, descripcion=?, precio=?, tipo=? WHERE idProductos=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setDouble(4, producto.getPrecio());
            ps.setString(5, producto.getTipo());
            ps.setInt(6, producto.getIdProductos());
            
            
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
    public Object eliminar(Object o) {
        PreparedStatement ps = null;       
        Connection con = getConexion();
        Modelo_Producto producto = getProducto(o);
            //Sentencia para eliminar el producto
        String sql = "DELETE FROM productos WHERE idProductos=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, producto.getIdProductos());
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
        Modelo_Producto producto = getProducto(o);
            //Sentencia para buscar
        String sql = "SELECT * FROM productos WHERE codigo=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, producto.getCodigo());
            rs = ps.executeQuery();
           
            if(rs.next()){
                producto.setIdProductos(Integer.parseInt(rs.getString("idProductos")));
                producto.setCodigo(Integer.parseInt(rs.getString("codigo")));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(Double.parseDouble(rs.getString("precio")));
                producto.setTipo(rs.getString("tipo"));
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
