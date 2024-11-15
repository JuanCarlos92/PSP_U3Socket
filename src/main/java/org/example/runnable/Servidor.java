package org.example.runnable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en el puerto " + PUERTO);

            while (true) {
                Socket cliente = servidor.accept();

                //Streams para leer del cliente y enviar respuestas
                BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
                System.out.println("Cliente conectado desde " + cliente.getInetAddress());

                //Crear y lanzar un hilo para gestionar al cliente
                new Thread(new ManejadorCliente(cliente)).start();
                String str = entrada.readLine();// Recibe la solicitud del cliente por el InputStream
                System.out.println("Cliente: " + str);// Envía a la salida estándar el mensaje del cliente
                System.out.println("Mensaje modificado a mayusculas: " + str.toUpperCase());
                salida.println(str.toUpperCase());// Le envía la respuesta al cliente por el OutputStream


            }
        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}