import java.util.Hashtable;
import java.util.Scanner;

/**
 *Métodos relacionados a la creación
  y eliminación de cuentas :DD*/
public class GestionClientes
{
    private Hashtable<String, Clientes> clientes;
     public GestionClientes(Hashtable<String, Clientes> clientes) {
        this.clientes = clientes;
    }
public void crearCuenta(Scanner sc){
        System.out.println("-------Creación de cuenta------");
        System.out.println("HII ¿Cuál es su nombre?");  
        String nombre=sc.nextLine().toLowerCase();
        System.out.println("ingrese su edad");
        int edad;
        try{
        edad=Integer.parseInt(sc.nextLine());}
        catch(NumberFormatException e){
            System.out.println("Se establecera a 1");
            edad=1;
        }
        Clientes nuevoCliente=new Clientes(nombre,edad);
        clientes.put(nuevoCliente.getNoCliente(),nuevoCliente);
        System.out.println("Nombre: " +nombre+" No.Cliente: "+nuevoCliente.getNoCliente());
        nuevoCliente.verificarDatosCredito(sc);//método verificar edad no creado// update ya hecho
        GuardarDatos.guardarClientes(clientes);
        
}
}
