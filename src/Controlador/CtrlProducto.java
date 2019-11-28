
package Controlador;

import Modelo.Dao;
import Modelo.Modelo_Producto;
import Modelo.ProductoDao;
import Vista.reg_prod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class CtrlProducto implements ActionListener {
    private Modelo_Producto mod;
    private Dao dao;
    private reg_prod frm;
    
    
    public CtrlProducto(reg_prod frm){
        this.mod = new Modelo_Producto();
        this.dao = new ProductoDao();
        this.frm = frm;
        this.frm.btnRegistrar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
        
    }
    
    public void iniciar(){
        frm.txtId.setVisible(false);
        frm.setTitle("Productos");
        frm.setLocationRelativeTo(null);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == frm.btnRegistrar){            
          
            mod.setCodigo(Integer.parseInt(frm.txtCodigo.getText()));
            mod.setNombre(frm.txtNombre.getText());
            mod.setDescripcion(frm.txtDescripcion.getText());
            mod.setPrecio(Double.parseDouble(frm.txtPrecio.getText()));
            mod.setTipo(frm.cbxTipo.getSelectedItem().toString());
            
            if((boolean)dao.guardar(mod)){
                JOptionPane.showMessageDialog(null, "Registro Guardado Exitosamente");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al Guardar");
                limpiar();
            }
        }
        
        if(e.getSource() == frm.btnModificar){
            mod.setIdProductos(Integer.parseInt(frm.txtId.getText()));
            mod.setCodigo(Integer.parseInt(frm.txtCodigo.getText()));
            mod.setNombre(frm.txtNombre.getText());
            mod.setDescripcion(frm.txtDescripcion.getText());
            mod.setPrecio(Double.parseDouble(frm.txtPrecio.getText()));
            mod.setTipo(frm.cbxTipo.getSelectedItem().toString());
            
            if((boolean)dao.modificar(mod)){
                JOptionPane.showMessageDialog(null, "Registro Modificado Exitosamente");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                limpiar();
            }
        }
        
        if(e.getSource() == frm.btnEliminar){
            mod.setIdProductos(Integer.parseInt(frm.txtId.getText()));
            
            
            if((boolean)dao.eliminar(mod)){
                JOptionPane.showMessageDialog(null, "Registro Eliminado Exitosamente");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
        }
        
        if(e.getSource() == frm.btnBuscar){
            mod.setCodigo(Integer.parseInt(frm.txtCodigo.getText()));
            
            
            
            if((boolean)dao.buscar(mod)){
                frm.txtId.setText(String.valueOf(mod.getIdProductos()));
                frm.txtCodigo.setText(String.valueOf(mod.getCodigo()));
                frm.txtNombre.setText(mod.getNombre());
                frm.txtDescripcion.setText(mod.getDescripcion());
                frm.txtPrecio.setText(String.valueOf(mod.getPrecio()));
                frm.cbxTipo.setSelectedItem(mod.getTipo());
                
            }else{
                JOptionPane.showMessageDialog(null, "No se encontro ningun resultado.");
                limpiar();
            }
        }

//        if (e.getSource() == frm.btnLimpiar){
//            limpiar();
//        }
    }
    
    public void limpiar(){
        frm.txtId.setText(null);
        frm.txtCodigo.setText(null);
        frm.txtNombre.setText(null);
        frm.txtDescripcion.setText(null);
        
        frm.txtPrecio.setText(null);
        frm.cbxTipo.setSelectedIndex(0);
    }
    
}
