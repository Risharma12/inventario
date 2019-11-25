
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Sql_Registro_Proveedores extends Conexion {
    
    
    //Método para realizar una insercion en la base de datos
     public boolean guardarprovedores(Modelo_Proveedores guardar_proveedores )
  {
   
     PreparedStatement ps = null;
     Connection con = getConexion();
       //Sentencia SQL para realizar una introduccion de datos
       String sql = "INSERT INTO proveedores (nombre_proveedores, domicilio_proveedores, telefono_proveedores, rfc_proveedores, ciudad_proveedores) VALUES(?, ?, ?, ?, ?)";
      
       
       try {
           ps = con.prepareStatement(sql);           
           ps.setString(1,guardar_proveedores.getNombre_proveedor());           
           ps.setString(2,guardar_proveedores.getDireccion_proveedor());  
           ps.setString(3,guardar_proveedores.getTelefono_proveedor());
           ps.setString(4,guardar_proveedores.getRfc_proveedor());
           ps.setString(5,guardar_proveedores.getCiudad_proveedor());
  
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
        String sql = "UPDATE proveedores SET nombre_proveedor=?, domicilio_provedor=?, telefono_proveedor=?, rfc_proveedor=?, ciudad_proveedor=?  WHERE id_proveedor=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, modificarprov.getNombre_proveedor());
            ps.setString(2,modificarprov.getDireccion_proveedor());  
            ps.setString(3,modificarprov.getTelefono_proveedor());
            ps.setString(4,modificarprov.getRfc_proveedor());
            ps.setString(5,modificarprov.getCiudad_proveedor());
           
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
        String sql = "SELECT * FROM proveedores WHERE nombre_proveedores=? ";

        try {
            ps.setString(1, buscarprov.getNombre_proveedor());
            ps.setString(2,buscarprov.getDireccion_proveedor());  
            ps.setString(3,buscarprov.getTelefono_proveedor());
            ps.setString(4,buscarprov.getRfc_proveedor());
            ps.setString(5,buscarprov.getCiudad_proveedor());
            rs = ps.executeQuery();
            
            if(rs.next())
            {
               buscarprov.setId_proveedor(Integer.parseInt(rs.getString("Id_proveedor")));
               buscarprov.setNombre_proveedor(rs.getString("nombre_proveedor"));
               buscarprov.setDireccion_proveedor(rs.getString("direccion_proveedor"));
               buscarprov.setTelefono_proveedor(rs.getString("telefono_proveedor"));
               buscarprov.setRfc_proveedor("rfc_proveedor");
               buscarprov.setCiudad_proveedor("ciudad_proveedor");
              
            
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
