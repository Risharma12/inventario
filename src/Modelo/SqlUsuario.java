package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlUsuario extends Conexion {

    public boolean guardarusuario(ModeloUsuario guardar) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO usuarios (nombre, usuario, contrasena, id_tipo) VALUES (?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, guardar.getNombre());
            ps.setString(2, guardar.getUsuario());
            ps.setString(3, guardar.getContrasena());
            ps.setInt(4, guardar.getId_tipo());

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
        }
    }

    public boolean modificarusuario(ModeloUsuario modificar) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE usuarios SET nombre=?, usuario=?, contrasena=? WHERE idusuarios=? ";

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, modificar.getNombre());
            ps.setString(2, modificar.getUsuario());
            ps.setString(3, modificar.getContrasena());
            ps.setInt(4, modificar.getIdusuarios());

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
        }
    }

    public boolean eliminarusuario(ModeloUsuario eliminar) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        //Sentencia para eliminar el producto
        String sql = "DELETE FROM usuarios WHERE idusuarios=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, eliminar.getIdusuarios());
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

    public boolean buscarusuario(ModeloUsuario buscar) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        //Sentencia para buscar
        String sql = "SELECT * FROM usuarios WHERE nombre=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, buscar.getNombre());
            rs = ps.executeQuery();

            if (rs.next()) {
                buscar.setIdusuarios(Integer.parseInt(rs.getString("idusuarios")));
                buscar.setNombre(rs.getString("nombre"));
                buscar.setUsuario(rs.getString("usuario"));
                buscar.setContrasena(rs.getString("contrasena"));

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

    public int existerusuario(String usuario) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT count(idusuarios) FROM usuarios WHERE usuario=?";

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, usuario);

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean login(ModeloUsuario usr) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT u.idusuarios, u.nombre, u.usuario, u.contrasena, u.id_tipo, t.nombre FROM usuarios AS u INNER JOIN tipo_usuario AS t ON u.id_tipo=idtipo_usuario WHERE usuario=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            rs = ps.executeQuery();

            if (rs.next()) {
                if (usr.getContrasena().equals(rs.getString(4))) {
                    usr.setIdusuarios(rs.getInt(1));
                    usr.setNombre(rs.getString(2));
                    usr.setId_tipo(rs.getInt(5));
                    usr.setNombre_tipo(rs.getString(6));
                    return true;

                } else {
                    return false;
                }

            }
            return false;

        } catch (SQLException ex) {
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
}
