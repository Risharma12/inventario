
package Controlador;

import Modelo.ModeloSalidas;
import Modelo.SalidasDao;
import Vista.salida_prod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Modelo.Dao;


public class CtrlSalidas implements ActionListener {
    private ModeloSalidas mod;
    private Dao dao;
    private salida_prod frm;

    public CtrlSalidas(salida_prod frm) {
        this.mod = new ModeloSalidas();
        this.dao = new SalidasDao();
        this.frm = frm;
        this.frm.btnRegistrar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
    }
    
    public void iniciar() {
        frm.txtId.setVisible(false);
        frm.setTitle("Salidas");
        frm.setLocationRelativeTo(null);
                
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == frm.btnRegistrar) {
           mod.setFecha(frm.txtFecha.getText());          
           mod.setCantidad(Double.parseDouble(frm.txtCantidad.getText()));
           mod.setProducto(frm.cbxProducto.getSelectedItem().toString());
          
           
           if((boolean)dao.guardar(mod)){
               JOptionPane.showMessageDialog(null, "Registro Guardado Exitosamente");
               limpiar();
           } else {
               JOptionPane.showMessageDialog(null, "Error al Guardar");
               limpiar();
           }
       } 
       
       if(e.getSource() == frm.btnModificar){
            mod.setIdsalidas(Integer.parseInt(frm.txtId.getText()));
            mod.setFecha(frm.txtFecha.getText());           
            mod.setCantidad(Double.parseDouble(frm.txtCantidad.getText()));
            mod.setProducto(frm.cbxProducto.getSelectedItem().toString());
            
            
            if((boolean)dao.modificar(mod)){
                JOptionPane.showMessageDialog(null, "Registro Modificado Exitosamente");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                limpiar();
            }
        }
       
       if(e.getSource() == frm.btnEliminar){
            mod.setIdsalidas(Integer.parseInt(frm.txtId.getText()));
            
            
            if((boolean)dao.eliminar(mod)){
                JOptionPane.showMessageDialog(null, "Registro Eliminado Exitosamente");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
        }
       
       if(e.getSource() == frm.btnBuscar){
            mod.setFecha(frm.txtFecha.getText());      
            
            
            if((boolean)dao.buscar(mod)){
                frm.txtId.setText(String.valueOf(mod.getIdsalidas()));
                frm.txtFecha.setText(mod.getFecha());                
                frm.txtCantidad.setText(String.valueOf(mod.getCantidad()));
                frm.cbxProducto.setSelectedItem(mod.getProducto());
               
                
            }else{
                JOptionPane.showMessageDialog(null, "No se encontro ningun resultado.");
                limpiar();
            }
        }
    }
    
    public void limpiar(){
        frm.txtId.setText(null);
        frm.txtCantidad.setText(null);       
        frm.txtFecha.setText(null);
        frm.cbxProducto.setSelectedIndex(0);
        
    }
    
}
