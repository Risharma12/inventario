
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Sql_Registro_Proveedores extends Conexion {
    
    
    //Método para realizar una insercion en la base de datos
     public boolean guardarproveedores(Modelo_Proveedores guardar_proveedores )
  {
   
     PreparedStatement ps = null;
     Connection con = getConexion();
       //Sentencia SQL para realizar una introduccion de datos
       String sql = "INSERT INTO proveedores (nombre, direccion, telefono, rfc, ciudad) VALUES(?, ?, ?, ?, ?)";
      
       
       try {
           ps = con.prepareStatement(sql);           
           ps.setString(1,guardar_proveedores.getNombre());           
           ps.setString(2,guardar_proveedores.getDireccion());  
           ps.setString(3,guardar_proveedores.getTelefono());
           ps.setString(4,guardar_proveedores.getRfc());
           ps.setString(5,guardar_proveedores.getCiudad());
  
           ps.execute();
           return true;
          
           
       } catch (SQLException ex) {
           Logger.getLogger(Sql_Registro_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
       
       
   }
        //Este método es para actualizar los datos de la tabla proveedores de base de datos
      public boolean modificarproveedores(Modelo_Proveedores modificarprov) {
        PreparedStatement ps = null;
        Connection con = getConexion();
            //Sentencia SQL para actualizar los datos
        String sql = "UPDATE proveedores SET nombre=?, direccion=?, telefono=?, rfc=?, ciudad=?  WHERE idproveedores=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, modificarprov.getNombre());
            ps.setString(2,modificarprov.getDireccion());  
            ps.setString(3,modificarprov.getTelefono());
            ps.setString(4,modificarprov.getRfc());
            ps.setString(5,modificarprov.getCiudad());
            ps.setInt(6, modificarprov.getIdproveedores());
           
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
    
      public boolean eliminarproveedores(Modelo_Proveedores eliminarprov) {
        PreparedStatement ps = null;       
        Connection con = getConexion();
        
            //Sentencia para eliminar el producto
        String sql = "DELETE FROM proveedores WHERE idproveedores=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, eliminarprov.getIdproveedores());
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
      
      public boolean buscarproveedores(Modelo_Proveedores buscarprov) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
            //Sentencia para realizar una busqueda en la base de datos
        String sql = "SELECT * FROM proveedores WHERE nombre=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, buscarprov.getNombre());
//            ps.setString(2,buscarprov.getDireccion());  
//            ps.setString(3,buscarprov.getTelefono());
//            ps.setString(4,buscarprov.getRfc());
//            ps.setString(5,buscarprov.getCiudad());
            rs = ps.executeQuery();
            
            if(rs.next())
            {
               buscarprov.setIdproveedores(Integer.parseInt(rs.getString("idproveedores")));
               buscarprov.setNombre(rs.getString("nombre"));
               buscarprov.setDireccion(rs.getString("direccion"));
               buscarprov.setTelefono(rs.getString("telefono"));
               buscarprov.setRfc(rs.getString("rfc"));
               buscarprov.setCiudad(rs.getString("ciudad"));
            
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
