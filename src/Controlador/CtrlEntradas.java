
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
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
        
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
           mod.setProducto(frm.cbxProducto.getSelectedItem().toString());
           mod.setProveedor(frm.cbxProveedor.getSelectedItem().toString());
           
           if(modC.guardarentrada(mod)){
               JOptionPane.showMessageDialog(null, "Registro Guardado Exitosamente");
               limpiar();
           } else {
               JOptionPane.showMessageDialog(null, "Error al Guardar");
               limpiar();
           }
       } 
       
       if(e.getSource() == frm.btnModificar){
            mod.setIdentradas(Integer.parseInt(frm.txtId.getText()));
            mod.setFecha(frm.txtFecha.getText());
            mod.setComentarios(frm.txtComentarios.getText());
            mod.setCantidad(Double.parseDouble(frm.txtCantidad.getText()));
            mod.setProducto(frm.cbxProducto.getSelectedItem().toString());
            mod.setProveedor(frm.cbxProveedor.getSelectedItem().toString());
            
            if(modC.modificarentrada(mod)){
                JOptionPane.showMessageDialog(null, "Registro Modificado Exitosamente");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                limpiar();
            }
        }
       
       if(e.getSource() == frm.btnEliminar){
            mod.setIdentradas(Integer.parseInt(frm.txtId.getText()));
            
            
            if(modC.eliminarentrada(mod)){
                JOptionPane.showMessageDialog(null, "Registro Eliminado Exitosamente");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
        }
       
       if(e.getSource() == frm.btnBuscar){
            mod.setFecha(frm.txtFecha.getText());
            
            
            
            if(modC.buscarentrada(mod)){
                frm.txtId.setText(String.valueOf(mod.getIdentradas()));
                frm.txtFecha.setText(mod.getFecha());
                frm.txtComentarios.setText(mod.getComentarios());
                frm.txtCantidad.setText(String.valueOf(mod.getCantidad()));
                frm.cbxProducto.setSelectedItem(mod.getProducto());
                frm.cbxProveedor.setSelectedItem(mod.getProveedor());
                
            }else{
                JOptionPane.showMessageDialog(null, "No se encontro ningun resultado.");
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
