
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {
    
  //Datos de la base de datos que usaremos
  private final String base ="inventario";
  private final String user ="root";
  private final String password ="root";
  private final String url ="jdbc:mysql://localhost:3306/" + base;
  private Connection con = null;
  
  public Connection getConexion()
{
    
        //Capturara los errores en la conexion con la base de datos en caso de que ocurran
      try {
          Class.forName("com.mysql.cj.jdbc.Driver" );
          con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);
          
          
          
      } catch (SQLException e) {
          System.err.println(e);
      } catch (ClassNotFoundException ex) {
          Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
      }
   return con;   
}

//    public void executeUpdate(String SQL) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
  



