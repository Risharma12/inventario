package Controlador;

import Modelo.ModeloUsuario;
import Modelo.SqlUsuario;
import Modelo.hash;
import Vista.reg_usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlUsuario implements ActionListener {

    private ModeloUsuario mod;
    private SqlUsuario modC;
    private reg_usuario frm;

    public CtrlUsuario(ModeloUsuario mod, SqlUsuario modC, reg_usuario frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        this.frm.btnIngresar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
    }

    public void iniciar() {
        frm.txtId.setVisible(false);
        frm.setTitle("Usuarios");
        frm.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frm.btnIngresar) {

            String pass = new String(frm.txtContrasena.getPassword());
            String passCon = new String(frm.txtConcontrasena.getPassword());

            if (pass.equals(passCon)) {

                if (modC.existerusuario(frm.txtUsuario.getText()) == 0) {

                    String nuevoPass = hash.sha1(pass);

                    mod.setNombre(frm.txtNombre.getText());
                    mod.setUsuario(frm.txtUsuario.getText());
                    mod.setContrasena(nuevoPass);
                    mod.setId_tipo(1);

                    if (modC.guardarusuario(mod)) {
                        JOptionPane.showMessageDialog(null, "Registro Guardado Exitosamente");
                        limpiar();

                    } else {
                        JOptionPane.showMessageDialog(null, "Error al guardar");
                        limpiar();

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El usuario ya existe");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
            }
        }

        if (e.getSource() == frm.btnModificar) {
            mod.setIdusuarios(Integer.parseInt(frm.txtId.getText()));
            mod.setNombre(frm.txtNombre.getText());
            mod.setUsuario(frm.txtUsuario.getText());
            mod.setContrasena(frm.txtContrasena.getText());

            if (modC.modificarusuario(mod)) {
                JOptionPane.showMessageDialog(null, "Registro Modificado Exitosamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                limpiar();
            }
        }

        if (e.getSource() == frm.btnEliminar) {
            mod.setIdusuarios(Integer.parseInt(frm.txtId.getText()));

            if (modC.eliminarusuario(mod)) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado Exitosamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
        }

        if (e.getSource() == frm.btnBuscar) {
            mod.setNombre(frm.txtNombre.getText());

            if (modC.buscarusuario(mod)) {
                frm.txtId.setText(String.valueOf(mod.getIdusuarios()));
                frm.txtNombre.setText(mod.getNombre());
                frm.txtUsuario.setText(mod.getUsuario());
                frm.txtContrasena.setText(mod.getContrasena());

            } else {
                JOptionPane.showMessageDialog(null, "No se encontro ningun resultado.");
                limpiar();
            }
        }
    }

    public void limpiar() {
        frm.txtId.setText(null);
        frm.txtUsuario.setText(null);
        frm.txtNombre.setText(null);
        frm.txtContrasena.setText(null);
        frm.txtConcontrasena.setText(null);
    }

}
