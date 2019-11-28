package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import Modelo.Dao;

public class ProveedoresDao extends Conexion implements Dao {

    private ModeloProveedores getProveedor(Object o) {
        ModeloProveedores proveedor;
        try {
            proveedor = (ModeloProveedores) o;
        } catch (Exception e) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.WARNING, null, e);
            return null;
        }
        return proveedor;
    }

    @Override
    public Object guardar(Object o) {

        PreparedStatement ps = null;
        Connection con = getConexion();
        ModeloProveedores proveedor = getProveedor(o);
        //Sentencia SQL para realizar una introduccion de datos
        String sql = "INSERT INTO proveedores (nombre, direccion, telefono, rfc, ciudad) VALUES(?, ?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getDireccion());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getRfc());
            ps.setString(5, proveedor.getCiudad());

            ps.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProveedoresDao.class.getName()).log(Level.SEVERE, null, ex);
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
        ModeloProveedores proveedor = getProveedor(o);
        //Sentencia SQL para actualizar los datos
        String sql = "UPDATE proveedores SET nombre=?, direccion=?, telefono=?, rfc=?, ciudad=?  WHERE idproveedores=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getDireccion());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getRfc());
            ps.setString(5, proveedor.getCiudad());
            ps.setInt(6, proveedor.getIdproveedores());

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
        ModeloProveedores proveedor = getProveedor(o);
        //Sentencia para eliminar el producto
        String sql = "DELETE FROM proveedores WHERE idproveedores=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, proveedor.getIdproveedores());
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
        ModeloProveedores proveedor = getProveedor(o);
        //Sentencia para realizar una busqueda en la base de datos
        String sql = "SELECT * FROM proveedores WHERE nombre=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, proveedor.getNombre());
//            
            rs = ps.executeQuery();

            if (rs.next()) {
                proveedor.setIdproveedores(Integer.parseInt(rs.getString("idproveedores")));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setRfc(rs.getString("rfc"));
                proveedor.setCiudad(rs.getString("ciudad"));

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
