package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMensaje {
    public static void main(String[] args) {
        int puerto = 6000;  // Puerto del servidor
        int maxClientes = 1; // Número máximo de clientes permitidos

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Esperando al cliente....");
            Socket clienteSocket = servidor.accept();
            System.out.println("Cliente conectado.");

            //Información del cliente conectado
            System.out.println("  Dirección IP remota: " + clienteSocket.getInetAddress().getHostAddress());
            System.out.println("  Puerto remoto: " + clienteSocket.getPort());
            System.out.println("  Puerto local: " + clienteSocket.getLocalPort());

            //Streams para leer del cliente y enviar respuestas
            BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true);

            while (true) {
                // Recibe la solicitud del cliente por el InputStream
                String str = entrada.readLine();
                // Envía a la salida estándar el mensaje del cliente
                System.out.println("Cliente: " + str);
                System.out.println("Mensaje modificado a mayusculas: " + str.toUpperCase());
                // Le envía la respuesta al cliente por el OutputStream
                salida.println(str.toUpperCase());
                // Si es "Adios" es que finaliza la comunicación
                if (str.equals("Adios")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
