
package Main;

import Controlador.CtrlProducto;
import Modelo.Modelo_Producto;
import Modelo.Sql_Registro_Producto;
import Vista.reg_prod;


public class Main {
    
    public static void main(String[] args) {
        Modelo_Producto mod = new Modelo_Producto();
        Sql_Registro_Producto modC = new Sql_Registro_Producto();
        reg_prod frm = new reg_prod();
        
        CtrlProducto ctrl = new CtrlProducto(mod, modC, frm);
        ctrl.iniciar();
        frm.setVisible(true);
    }
    
}
