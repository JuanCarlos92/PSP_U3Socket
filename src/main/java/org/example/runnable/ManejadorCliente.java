package org.example.runnable;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ManejadorCliente implements Runnable {
    private final Socket cliente;

    public ManejadorCliente(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try (
                BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true)
        ) {
            String mensaje;
            while ((mensaje = entrada.readLine()) != null) {
                if (mensaje.equals("*")) {
                    System.out.println("Finalizando comunicación con el cliente.");
                    break;
                }

                String respuesta = mensaje.toUpperCase();
                salida.println(respuesta);
            }
        } catch (Exception e) {
            System.err.println("Error al gestionar cliente: " + e.getMessage());
        } finally {
            try {
                cliente.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }
}
