
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SqlEntradas extends Conexion {
    
    public boolean guardarentrada (ModeloEntradas guardar) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO entradas (fecha, comentarios, cantidad, prod_ent, prov_ent) VALUES (?, ?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, guardar.getFecha());
            ps.setString(2, guardar.getComentarios());
            ps.setDouble(3, guardar.getCantidad());
            ps.setInt(4, guardar.getProd_ent());
            ps.setInt(5, guardar.getProv_ent());
            
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
        String sql = "UPDATE entradas SET fecha=?, comentarios=?, cantidad=?, prod_ent=?, prov_ent=? WHERE identradas=? ";
        
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, modificar.getFecha());
            ps.setString(2, modificar.getComentarios());
            ps.setDouble(3, modificar.getCantidad());
            ps.setInt(4, modificar.getProd_ent());
            ps.setInt(5, modificar.getProv_ent());
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
}
