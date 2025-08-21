//Guarda y carga datos obj

import java.io.*;
import java.util.Hashtable;

public class GuardarDatos {
    private static final String ARCHIVO_CLIENTES = "clientes.obj";

    public static void guardarClientes(Hashtable<String, Clientes> clientes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_CLIENTES))) {
            oos.writeObject(clientes);
            System.out.println("Datos de clientes guardados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar datos: " + e.getMessage());
        }
    }

    public static Hashtable<String, Clientes> cargarClientes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_CLIENTES))) {
            Hashtable<String, Clientes> clientes = (Hashtable<String, Clientes>) ois.readObject();

            int maxNoCliente = 0;
            for (String key : clientes.keySet()) {
                if (key.startsWith("C-")) {
                    try {
                        int num = Integer.parseInt(key.substring(2));
                        if (num > maxNoCliente) maxNoCliente = num;
                    } catch (NumberFormatException e) {}
                }
            }

            try {
                java.lang.reflect.Field field = Clientes.class.getDeclaredField("contadorNoCliente");
                field.setAccessible(true);
                field.set(null, maxNoCliente + 1);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.err.println("Error ajustando contador: " + e.getMessage());
            }

            System.out.println("Datos de clientes cargados exitosamente.");
            return clientes;

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Se creará uno nuevo al guardar.");
            return new Hashtable<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar datos: " + e.getMessage());
            return new Hashtable<>();
        }
    }
}
