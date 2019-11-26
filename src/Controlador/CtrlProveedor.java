package Controlador;

import Modelo.Modelo_Proveedores;
import Modelo.Sql_Registro_Proveedores;
import Vista.reg_prov;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.HashSet;
//import java.util.Set;
import javax.swing.JOptionPane;

public class CtrlProveedor implements ActionListener {

    private Modelo_Proveedores mod;
    private Sql_Registro_Proveedores modC;
    private reg_prov frm;

    public CtrlProveedor(Modelo_Proveedores mod, Sql_Registro_Proveedores modC, reg_prov frm) {
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
        frm.setTitle("Productos");
        frm.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnIngresar) {
            mod.setNombre(frm.txtNombre.getText());
            mod.setDireccion(frm.txtNombre.getText());
            mod.setTelefono(frm.txtTelefono.getText());
            mod.setRfc(frm.txtRfc.getText());
            mod.setCiudad(frm.txtCiudad.getText());

            if (modC.guardarproveedores(mod)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado Exitosamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
                limpiar();
            }
        }

        if (e.getSource() == frm.btnModificar) {
            mod.setIdproveedores(Integer.parseInt(frm.txtId.getText()));
            mod.setNombre(frm.txtNombre.getText());
            mod.setDireccion(frm.txtDireccion.getText());
            mod.setTelefono(frm.txtTelefono.getText());
            mod.setRfc(frm.txtRfc.getText());
            mod.setCiudad(frm.txtCiudad.getText());

            if (modC.modificarproveedores(mod)) {
                JOptionPane.showMessageDialog(null, "Registro Modificado Exitosamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                limpiar();
            }
        }

        if (e.getSource() == frm.btnEliminar) {
            mod.setIdproveedores(Integer.parseInt(frm.txtId.getText()));

            if (modC.eliminarproveedores(mod)) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado Exitosamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
        }

        if (e.getSource() == frm.btnBuscar) {
            mod.setNombre(frm.txtNombre.getText());

            if (modC.buscarproveedores(mod)) {
                frm.txtId.setText(String.valueOf(mod.getIdproveedores()));

                frm.txtNombre.setText(mod.getNombre());
                frm.txtDireccion.setText(mod.getDireccion());
                frm.txtTelefono.setText(mod.getTelefono());
                frm.txtRfc.setText(mod.getRfc());
                frm.txtCiudad.setText(mod.getCiudad());

            } else {
                JOptionPane.showMessageDialog(null, "No se encontro ningun resultado.");
                limpiar();
            }
        }

//        if (e.getSource() == frm.btnLimpiar){
//            limpiar();
//        }
    }

    public void limpiar() {
        frm.txtId.setText(null);
        frm.txtNombre.setText(null);
        frm.txtDireccion.setText(null);
        frm.txtTelefono.setText(null);

        frm.txtRfc.setText(null);
        frm.txtCiudad.setText(null);
    }
}
