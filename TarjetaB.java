import java.util.Scanner;
import java.io.Serializable;
import java.util.Hashtable;

abstract class TarjetaB implements Serializable {
    private String noTarjeta;
    private String nombre;
    private double saldo;
     public TarjetaB() {
        this.noTarjeta = "";
        this.nombre = "";
        this.saldo = 0.0;
    }
    // Constructor con parámetros
    public TarjetaB(String numeroTarjeta, String titular, double saldo) {
        this.noTarjeta = numeroTarjeta;
        this.nombre = titular;
        this.saldo = saldo;
    }

    // Métodos getter (encapsulamiento)
    public String getNoTarjeta() {
        return noTarjeta;
    }
    public String getNombre() {
        return nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    // Métodos setter (encapsulamiento)
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    // Método para mostrar información básica de la tarjeta
    public void mostrarInformacion() {
        System.out.println("Número de Tarjeta: " + noTarjeta);
        System.out.println("Titular: " + nombre);
        System.out.println("Saldo: $" + String.format("%.2f", saldo));
    }
     public abstract void mostrarMenu(Scanner sc, Hashtable<String, Clientes> clientes);
    // Método abstracto para realizar un pago 
    public abstract void realizarPago(double monto);

    // Método abstracto para retirar efectivo 
    public abstract void retirarEfectivo(double monto);
}

