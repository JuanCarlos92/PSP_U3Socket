package org.example.runnable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PUERTO);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado al servidor. Escriba mensajes para enviar, o '*' para terminar.");

            String mensaje;
            while (true) {
                System.out.print("Mensaje: ");
                mensaje = scanner.nextLine();
                salida.println(mensaje);

                if (mensaje.equals("*")) {
                    System.out.println("Terminando comunicación.");
                    break;
                }

                String respuesta = entrada.readLine();
                System.out.println("Respuesta del servidor: " + respuesta);
            }
        } catch (Exception e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }

}

