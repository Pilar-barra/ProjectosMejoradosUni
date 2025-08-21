import java.util.Hashtable;
import java.util.Scanner;

public class GestionMenus {
    private Hashtable<String, Clientes> clientes;

    public GestionMenus(Hashtable<String, Clientes> clientes) {
        this.clientes = clientes;
    }

    public void mostrarMenuPrincipal() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Bienvenido al Banco ---");
            System.out.println("1. Soy un cliente existente");
            System.out.println("2. Quiero crear una cuenta");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    accederClienteExistente(sc);
                    break;
                case 2:
                      GestionClientes gestor = new GestionClientes(clientes); 
                      gestor.crearCuenta(sc);
                    break;
                case 3:
                    System.out.println("Gracias por usar WisdomU Banco. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta otra vez.");
            }
        } while (opcion != 3);
        sc.close();
    }

    
    public void accederClienteExistente(Scanner sc) {
        System.out.print("Ingrese su número de cliente (ej. C-1): ");
        String noCliente = sc.nextLine();

        Clientes cliente = clientes.get(noCliente);
        if (cliente != null) {
            System.out.println("Bienvenido de vuelta, " + cliente.getNombre() + "!");
            cliente.menuCliente(sc,clientes);  // Va a método en Clientes
        } else {
            System.out.println("Cliente no encontrado. Verifica el número.");
        }
    }
}

