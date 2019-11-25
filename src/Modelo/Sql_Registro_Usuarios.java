
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Sql_Registro_Usuarios extends Conexion {
    
    
     public boolean guardarusuario(Modelo_Usuarios guardar_usuarios )
  {
   
     PreparedStatement ps = null;
     Connection con = getConexion();
       //Sentencia SQL para realizar una introduccion de datos
       String sql = "INSERT INTO usuarios (nombre_usuario, domicilio_usuario, usuario_usuario, contrasena_usuario) VALUES(?, ?, ?, ?)";
      
       
       try {
           ps = con.prepareStatement(sql);           
           ps.setString(1,guardar_usuarios.getNombre_usuario());           
           ps.setString(2,guardar_usuarios.getDomicilio_usuario());  
           ps.setString(3,guardar_usuarios.getUsuario_usuario());
           ps.setString(4,guardar_usuarios.getContrasena_usuario());
  
           ps.execute();
           return true;
          
           
       } catch (SQLException ex) {
           Logger.getLogger(Sql_Registro_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
       
       
   }
        //Este método es para actualizar los datos de la tabla proveedores de base de datos
      public boolean modificarusuarios(Modelo_Usuarios modificar_usuario) {
        PreparedStatement ps = null;
        Connection con = getConexion();
            //Sentencia SQL para actualizar los datos
        String sql = "UPDATE usuarios SET nombre_usuario=?, domicilio_usuario=?, usuario_usuario=?, contrasena_contrasena=?  WHERE id_usuario=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,modificar_usuario.getNombre_usuario());
            ps.setString(2,modificar_usuario.getDomicilio_usuario());  
            ps.setString(3,modificar_usuario.getUsuario_usuario());
            ps.setString(4,modificar_usuario.getContrasena_usuario());
            
           
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
      
      
      public boolean buscarusuario(Modelo_Usuarios buscar_usuario) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
            //Sentencia para realizar una busqueda en la base de datos
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario=? ";

        try {
            ps.setString(1, buscar_usuario.getNombre_usuario());
            ps.setString(2,buscar_usuario.getDomicilio_usuario());  
            ps.setString(3,buscar_usuario.getUsuario_usuario());
            ps.setString(4,buscar_usuario.getContrasena_usuario());
            rs = ps.executeQuery();
            
            if(rs.next())
            {
               buscar_usuario.setId_usuario(Integer.parseInt(rs.getString("Id_usuario")));
               buscar_usuario.setDomicilio_usuario(rs.getString("domicilio_usuario"));
               buscar_usuario.setUsuario_usuario(rs.getString("usuario_usuario"));
               buscar_usuario.setContrasena_usuario(rs.getString("contrasena_usuario"));
               
              
            
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
