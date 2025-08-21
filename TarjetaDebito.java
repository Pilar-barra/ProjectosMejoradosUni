import java.util.Scanner;
import java.io.Serializable;
import java.util.Hashtable;

class TarjetaDebito extends TarjetaB implements Serializable {
    // Constructor por defecto
    public TarjetaDebito() {
        super(); // Llama al constructor por defecto de la clase padre
    }
    // sobrecarga
    public TarjetaDebito(String noTarjeta, String nombre, double saldo) {
        super(noTarjeta, nombre, saldo);
    }
    @Override
    public void mostrarMenu(Scanner sc, Hashtable<String, Clientes> clientes){
        boolean volver=false; 
        int opMenuD;
               do{
        System.out.println("\n--- Tarjeta de Débito ---");
        System.out.println("1-.Mostrar información.");
        System.out.println("2-.Realizar transferencia.");
        System.out.println("3-.Retirar efectivo.");
        System.out.println("4-. Ingresar fondos.");
        System.out.println("5-. Salir.");
        System.out.println("¿Qué operación quiere hacer?");
        opMenuD = sc.nextInt();
        sc.nextLine();
        switch(opMenuD){
         case 1: {
            mostrarInformacion();}
            break; 
            case 2: {
            try{
                System.out.println("Ingrese el monto de la transferencia");
                double montoPago=sc.nextDouble();
                realizarPago(montoPago);
                GuardarDatos.guardarClientes(clientes);
            }catch(IllegalArgumentException e){
               System.out.println("Error: "+ e.getMessage());}
            }break;
            case 3:{
                try{
                    System.out.println("ingrese el monto a retirar");
                    double montoRetiro=sc.nextDouble();
                    sc.nextLine();
                    retirarEfectivo(montoRetiro);
                    GuardarDatos.guardarClientes(clientes);
                 
                }catch(IllegalArgumentException e){
                     System.out.println("Error: "+ e.getMessage());}
                }break;
            case 4:{
                try{
                    System.out.println("ingrese el monto a depositar:");
                    double montoDeposito=sc.nextDouble();
                    sc.nextLine();
                    depositar(montoDeposito);
                     GuardarDatos.guardarClientes(clientes);
                }catch(IllegalArgumentException e){
                     System.out.println("Error: "+ e.getMessage());}
                }break;
                case 5:{
                    System.out.println("Volviendo a selección de tarjetas");
               }
                break;
                default:
                    System.out.println("Opción no valida");
                    break;
                    
                }
        }
        while(volver!=true);
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("\n--- Tarjeta de Débito ---");
        super.mostrarInformacion(); // Llama al método mostrarInformacion de la clase padre
    }
    
    public void realizarPago(double monto) {
    if (monto <= 0) {
        throw new IllegalArgumentException("El monto del pago debe ser mayor que cero.");
    }

    if (monto > getSaldo()) {
        throw new IllegalArgumentException("Saldo insuficiente para realizar el pago.");
    }

    setSaldo(getSaldo() - monto); // modifica el saldo real

    System.out.println("Pago realizado: $" + String.format("%.2f", monto));
}

   @Override
public void retirarEfectivo(double monto) {
    if (monto <= 0) {
        throw new IllegalArgumentException("El monto a retirar debe ser mayor que cero.");
    }

    if (monto > getSaldo()) {
        throw new IllegalArgumentException("Saldo insuficiente para realizar el retiro.");
    }

    setSaldo(getSaldo() - monto);
    System.out.println("Retiro de efectivo de $" + String.format("%.2f", monto) + " realizado. Nuevo saldo: $" + String.format("%.2f", getSaldo()));
}

   public void depositar(double monto) {
    if (monto <= 0) {
        throw new IllegalArgumentException("El monto a depositar debe ser mayor que cero.");
    }
    setSaldo(getSaldo() + monto);
    System.out.println("Depósito de $" + String.format("%.2f", monto) + " realizado. Nuevo saldo: $" + String.format("%.2f", getSaldo()));
}
}