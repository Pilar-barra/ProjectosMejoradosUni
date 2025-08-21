import java.util.Scanner;
import java.io.Serializable;
import java.util.Hashtable;

// Clase Hija: TarjetaCredito
class TarjetaCredito extends TarjetaB implements Serializable {
    private double limiteCredito;
    private double deudaActual;
    private double tasaInteres;
    private double pagoDeuda;
    public TarjetaCredito() {
        super(); // Llama al constructor por defecto de la clase padre
        this.limiteCredito = 0.0;
        this.deudaActual = 0.0;
        this.tasaInteres = 0.12; // 12% 
        this.pagoDeuda=0.0;
    }

    //  (sobrecarga)
    public TarjetaCredito(String numeroTarjeta, String titular, double limiteCredito) {
        super(numeroTarjeta, titular, 0.0); // Saldo inicial en crédito es 0
        this.limiteCredito = limiteCredito;
        this.deudaActual = 0.0;
        this.tasaInteres = 0.12;
    }

    public TarjetaCredito(String numeroTarjeta, String titular, double limiteCredito, double tasaInteres) {
        super(numeroTarjeta, titular, 0.0);
        this.limiteCredito = limiteCredito;
        this.deudaActual = 0.0;
        this.tasaInteres = tasaInteres;
    }

    // Métodos get de la tarjeta de crédito
    public double getLimiteCredito() {
        return limiteCredito;
    }
    public double getDeudaActual() {
        return deudaActual;
    }
    public double getTasaInteres() {
        return tasaInteres;
    }

    // Método set de la Tarjeta de Crédito 
    public void setDeudaActual(double deudaActual) {
        this.deudaActual = deudaActual;
    }
 
    @Override
    public void mostrarInformacion() {
        System.out.println("\n--- Tarjeta de Crédito ---");
        super.mostrarInformacion(); // Llama al método mostrarInformacion de la clase padre
        System.out.println("Límite de Crédito: $" + String.format("%.2f", limiteCredito));
        System.out.println("Deuda Actual: $" + String.format("%.2f", deudaActual));
        System.out.println("Tasa de Interés: " + (tasaInteres * 100) + "%");
    }
    @Override 
    public void mostrarMenu(Scanner sc,Hashtable<String, Clientes> clientes){
          int  opMenu; 
          boolean volver=false;
          do{ 
          System.out.println("\n--- Tarjeta de Crédito ---");
          System.out.println("1-.Realizar transferencia");
          System.out.println("2-.Retirar efectivo");
          System.out.println("3-.Pagar Tarjeta(Fraccióln");
          System.out.println("4-.Pagar el total de la deuda");
          System.out.println("5-.Mostrar información");
          System.out.println("6-.Salir");
          System.out.println("¿Qué desea hacer?");
            opMenu = sc.nextInt();
            sc.nextLine();
              switch(opMenu){
               case 1: {
                  System.out.println("Elegiste transferir.");
                try {
                    System.out.print("Ingrese el monto del pago: ");
                    double montoPago = sc.nextDouble();
                    sc.nextLine();
                    realizarPago(montoPago);
                    GuardarDatos.guardarClientes(clientes);

                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                    }
                 }break;
                case 2:{ System.out.println("Elegiste retirar.");
                    try {
                    System.out.print("Ingrese el monto a retirar: ");
                    double montoRetiro = sc.nextDouble();
                    sc.nextLine();
                    retirarEfectivo(montoRetiro);
                    GuardarDatos.guardarClientes(clientes);

                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }}
                break;
                case 3: {
                     System.out.println("Elegiste pagar una parte.");
                    try {
                    System.out.print("Ingrese el monto a pagar a la tarjeta: ");
                    double pagoDeuda = sc.nextDouble();
                    sc.nextLine();
                    pagarTarjeta(pagoDeuda);
                    GuardarDatos.guardarClientes(clientes);

                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                  }break;
               case 4: {
                    System.out.println("Elegiste pagar toda la deuda.");
                    try{
                        pagarDeudaT(sc);
                        GuardarDatos.guardarClientes(clientes);

                    }catch(IllegalArgumentException e){
                     System.out.println("Error: " + e.getMessage());}
                }break;
                case 5:{
                    mostrarInformacion();
                    break;
                }
                case 6:{
                    System.out.println("Volviendo a selección de tarjetas");
                }
                break;
                default:{
                System.out.println("Opción no valida");
                }break;
      
                }
            } while(volver!=true);
       
    }
    @Override
    public void realizarPago(double monto) {//transferencia
    if (monto <= 0) {
        throw new IllegalArgumentException("El monto del pago debe ser mayor que cero.");
    }
    if ((deudaActual + monto) > limiteCredito) {
        throw new IllegalArgumentException("El pago excede el límite de crédito.");
    }
    deudaActual += monto;
    System.out.println("Tranferencia  realizado: $" + String.format("%.2f", monto));
}
 
    @Override
    public void retirarEfectivo(double monto) {
    if (monto <= 0) {
        throw new IllegalArgumentException("El monto debe ser positivo.");
    }
    if (monto > (limiteCredito - deudaActual)) {
        throw new IllegalArgumentException("No tienes suficiente crédito para retirar esa cantidad.");
    }
    deudaActual += monto;
    System.out.println("Retiro exitoso: $" + String.format("%.2f", monto));
}

    public void pagarTarjeta(double monto) {
    if (monto <= 0) {
        throw new IllegalArgumentException("El pago debe ser mayor que cero.");
    }
    if (monto > deudaActual) {
        throw new IllegalArgumentException("No puedes pagar más de la deuda actual.");
    }
    deudaActual -= monto;
    System.out.println("Pago aplicado: $" + String.format("%.2f", monto));
}
public void pagarDeudaT(Scanner sc){
    System.out.println("Deuda actual: $"+deudaActual);
    System.out.println("Desea pagar su deuda total? S, N");
    String opDeuda = sc.nextLine();
    if  (opDeuda.equalsIgnoreCase("S")){
        try{
            pagarTarjeta(deudaActual);
     }catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }//
            else{System.out.println("No se realizó ningún pago.");
            }
            }
        }

