
package Main;

import Controlador.CtrlEntradas;
import Controlador.CtrlProveedor;
import Modelo.ModeloEntradas;
import Modelo.Modelo_Proveedores;
import Modelo.SqlEntradas;
import Modelo.Sql_Registro_Proveedores;
import Vista.entrada_prod;
import Vista.reg_prov;


public class Main {
    
    public static void main(String[] args) {
//        new Principal().setVisible(true);
//        Modelo_Producto mod = new Modelo_Producto();
//        Sql_Registro_Producto modC = new Sql_Registro_Producto();
//        reg_prod frm = new reg_prod();
//        
//        CtrlProducto ctrl = new CtrlProducto(mod, modC, frm);
//        ctrl.iniciar();
//        frm.setVisible(true);

//        Modelo_Proveedores mod2 = new Modelo_Proveedores();
//        Sql_Registro_Proveedores modC2 = new Sql_Registro_Proveedores();
//        reg_prov frm2 = new reg_prov();
//        
//        CtrlProveedor ctrl2 = new CtrlProveedor(mod2, modC2, frm2);
//        ctrl2.iniciar();
//        frm2.setVisible(true);
        
        ModeloEntradas mod3 = new ModeloEntradas();
        SqlEntradas modC3 = new SqlEntradas();
        entrada_prod frm3 = new entrada_prod();
        
        CtrlEntradas ctrl3 = new CtrlEntradas(mod3, modC3, frm3);
        ctrl3.iniciar();
        frm3.setVisible(true);
    }
    
}
