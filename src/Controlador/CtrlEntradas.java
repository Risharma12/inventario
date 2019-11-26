
package Controlador;

import Modelo.ModeloEntradas;
import Modelo.SqlEntradas;
import Vista.entrada_prod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class CtrlEntradas implements ActionListener {
    
    private ModeloEntradas mod;
    private SqlEntradas modC;
    private entrada_prod frm;

    public CtrlEntradas(ModeloEntradas mod, SqlEntradas modC, entrada_prod frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        this.frm.btnIngresar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        
    }
    
    public void iniciar() {
        frm.txtId.setVisible(false);
        frm.setTitle("Entradas");
        frm.setLocationRelativeTo(null);
                
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == frm.btnIngresar) {
           mod.setFecha(frm.txtFecha.getText());
           mod.setComentarios(frm.txtComentarios.getText());
           mod.setCantidad(Double.parseDouble(frm.txtCantidad.getText()));
           mod.setProd_ent(frm.cbxProducto.getSelectedItem());
           mod.setProv_ent(frm.cbxProveedor.getSelectedIndex());
           
           if(modC.guardarentrada(mod)){
               JOptionPane.showMessageDialog(null, "Registro Guardado Exitosamente");
               limpiar();
           } else {
               JOptionPane.showMessageDialog(null, "Error al Guardar");
               limpiar();
           }
       } 
    }
    
    public void limpiar(){
        frm.txtId.setText(null);
        frm.txtCantidad.setText(null);
        frm.txtComentarios.setText(null);
        frm.txtFecha.setText(null);
        frm.cbxProducto.setSelectedIndex(0);
        frm.cbxProveedor.setSelectedIndex(0);
    }
    
}
