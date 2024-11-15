package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class B_MiPrimerServidor {
    public static void main(String[] args) {
        int puerto = 6000;  // Puerto del servidor
        int maxClientes = 5; // Número máximo de clientes permitidos

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado en el puerto " + puerto);

            for (int i = 0; i < maxClientes; i++) {
                System.out.println("Esperando al cliente " + (i + 1) + "...");
                Socket clienteSocket = servidor.accept();

                // Información del cliente conectado
                System.out.println("Cliente " + (i + 1) + " conectado.");
                System.out.println("  Dirección IP remota: " + clienteSocket.getInetAddress().getHostAddress());
                System.out.println("  Puerto remoto: " + clienteSocket.getPort());
                System.out.println("  Puerto local: " + clienteSocket.getLocalPort());

                // Mantener la conexión abierta un momento
                clienteSocket.close();
            }

            System.out.println("Se han conectado los " + maxClientes + " clientes permitidos. Cerrando servidor.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}