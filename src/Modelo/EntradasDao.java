package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EntradasDao extends Conexion implements Dao {

    private ModeloEntradas getEntrada(Object o) {
        ModeloEntradas entrada;
        try {
            entrada = (ModeloEntradas) o;
        } catch (Exception e) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.WARNING, null, e);
            return null;
        }
        return entrada;
    }

    @Override
    public Object guardar(Object o) {

        PreparedStatement ps = null;
        Connection con = getConexion();
        ModeloEntradas entrada = getEntrada(o);
        String sql = "INSERT INTO entradas (fecha, comentarios, cantidad, producto, proveedor) VALUES (?, ?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, entrada.getFecha());
            ps.setString(2, entrada.getComentarios());
            ps.setDouble(3, entrada.getCantidad());
            ps.setString(4, entrada.getProducto());
            ps.setString(5, entrada.getProveedor());

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
        ModeloEntradas entrada = getEntrada(o);
        String sql = "UPDATE entradas SET fecha=?, comentarios=?, cantidad=?, producto=?, proveedor=? WHERE identradas=? ";

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, entrada.getFecha());
            ps.setString(2, entrada.getComentarios());
            ps.setDouble(3, entrada.getCantidad());
            ps.setString(4, entrada.getProducto());
            ps.setString(5, entrada.getProveedor());
            ps.setInt(6, entrada.getIdentradas());

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
    public Object eliminar(Object o) {

        PreparedStatement ps = null;
        Connection con = getConexion();
        ModeloEntradas entrada = getEntrada(o);
        //Sentencia para eliminar el producto
        String sql = "DELETE FROM entradas WHERE identradas=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, entrada.getIdentradas());
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
        ModeloEntradas entrada = getEntrada(o);
        //Sentencia para buscar
        String sql = "SELECT * FROM entradas WHERE fecha=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, entrada.getFecha());
            rs = ps.executeQuery();

            if (rs.next()) {
                entrada.setIdentradas(Integer.parseInt(rs.getString("identradas")));
                entrada.setFecha(rs.getString("fecha"));
                entrada.setComentarios(rs.getString("comentarios"));
                entrada.setCantidad(Double.parseDouble(rs.getString("cantidad")));
                entrada.setProducto(rs.getString("producto"));
                entrada.setProveedor(rs.getString("proveedor"));
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
