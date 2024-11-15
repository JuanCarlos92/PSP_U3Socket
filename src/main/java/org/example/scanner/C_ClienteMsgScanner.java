package org.example.scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class C_ClienteMsgScanner {
    public static void main(String[] args) {
        String host = "46.6.203.242"; // Dirección IP del servidor
        int puerto = 6000; // Puerto del servidor

        try (Socket cliente = new Socket(host, puerto)) {
            System.out.println("Conectado al servidor.");
            System.out.println("Puerto local (cliente): " + cliente.getLocalPort());
            System.out.println("Puerto remoto (servidor): " + cliente.getPort());

            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            String mensajeCliente;
            do {
                System.out.print("Escribe un mensaje (o 'salir' para desconectar): ");
                mensajeCliente = scanner.nextLine();
                salida.println(mensajeCliente);

                if (!mensajeCliente.equalsIgnoreCase("salir")) {
                    // Leer respuesta del servidor
                    String respuestaServidor = entrada.readLine();
                    System.out.println("Respuesta del servidor: " + respuestaServidor);
                }

            } while (!mensajeCliente.equalsIgnoreCase("salir"));

            System.out.println("Cliente desconectado.");
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}