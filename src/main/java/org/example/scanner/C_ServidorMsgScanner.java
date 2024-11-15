package org.example.scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class C_ServidorMsgScanner {
    public static void main(String[] args) {
        int puerto = 6000;  // Puerto del servidor
        int maxClientes = 1; // Número máximo de clientes permitidos

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado en el puerto " + puerto);

            System.out.println("Esperando al cliente....");
                Socket clienteSocket = servidor.accept();
                System.out.println("Cliente conectado.");

                //Información del cliente conectado
                System.out.println("  Dirección IP remota: " + clienteSocket.getInetAddress().getHostAddress());
                System.out.println("  Puerto remoto: " + clienteSocket.getPort());
                System.out.println("  Puerto local: " + clienteSocket.getLocalPort());

                BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true);

                String mensajeRecibido;
                while ((mensajeRecibido = entrada.readLine()) != null) {
                    System.out.println("Mensaje del cliente: " + mensajeRecibido);

                    if (mensajeRecibido.equalsIgnoreCase("salir")) {
                        System.out.println("Cliente ha cerrado la conexión.");
                        break;
                    }

                    // Enviar respuesta al cliente
                    String mensajeServidor = "Mensaje recibido: " + mensajeRecibido;
                    salida.println(mensajeServidor);
                }

                clienteSocket.close();


            System.out.println("Se han conectado los " + maxClientes + " clientes permitidos. Cerrando servidor.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}