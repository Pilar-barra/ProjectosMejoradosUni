
/** solo debe de ir aqui 
 * 
cargarClientes()
guardarClientes()
mostrarMenuPrincipal()
guardarClientes() */
import java.util.Hashtable;

public class Banco {
    public static void main(String[] args) {
        Hashtable<String, Clientes> clientesRegistrados = GuardarDatos.cargarClientes();
        GestionMenus menu = new GestionMenus(clientesRegistrados);
        menu.mostrarMenuPrincipal();
        GuardarDatos.guardarClientes(clientesRegistrados);
    }
}


    
   

   
