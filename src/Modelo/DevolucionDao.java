package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DevolucionDao extends Conexion implements Dao {

    private ModeloDevolucion getDevolucion(Object o) {
        ModeloDevolucion dev;
        try {
            dev = (ModeloDevolucion) o;
        } catch (Exception e) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.WARNING, null, e);
            return null;
        }
        return dev;
    }

    @Override
    public Object guardar(Object o) {

        PreparedStatement ps = null;
        Connection con = getConexion();
        ModeloDevolucion dev = getDevolucion(o);
        //Senetencia para insertar datos en la tabla de productos
        String sql = "INSERT INTO devoluciones (fecha, comentarios, cantidad, producto, proveedor) VALUES(?, ?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, dev.getFecha());
            ps.setString(2, dev.getComentarios());
            ps.setDouble(3, dev.getCantidad());
            ps.setString(4, dev.getProducto());
            ps.setString(5, dev.getProveedor());

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

    @Override
    public Object modificar(Object o) {

        PreparedStatement ps = null;
        Connection con = getConexion();
        ModeloDevolucion dev = getDevolucion(o);
        //Sentencia para actualizar datos de la tabla productos en la base de datos
        String sql = "UPDATE devoluciones SET fecha=?, comentarios=?, cantidad=?, producto=?, proveedor=? WHERE iddevoluciones=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, dev.getFecha());
            ps.setString(2, dev.getComentarios());
            ps.setDouble(3, dev.getCantidad());
            ps.setString(4, dev.getProducto());
            ps.setString(5, dev.getProveedor());
            ps.setInt(6, dev.getIddevoluciones());

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
        ModeloDevolucion dev = getDevolucion(o);
        //Sentencia para eliminar el producto
        String sql = "DELETE FROM devoluciones WHERE iddevoluciones=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, dev.getIddevoluciones());
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
        ModeloDevolucion dev = getDevolucion(o);
        //Sentencia para buscar
        String sql = "SELECT * FROM devoluciones WHERE fecha=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, dev.getFecha());
            rs = ps.executeQuery();

            if (rs.next()) {
                dev.setIddevoluciones(Integer.parseInt(rs.getString("iddevoluciones")));
                dev.setFecha(rs.getString("fecha"));
                dev.setComentarios(rs.getString("comentarios"));
                dev.setCantidad(Double.parseDouble(rs.getString("cantidad")));
                dev.setProducto(rs.getString("producto"));
                dev.setProveedor(rs.getString("proveedor"));
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
