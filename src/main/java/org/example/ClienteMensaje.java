package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteMensaje {
    public static void main(String[] args) throws IOException {
        String host = "localhost"; // Dirección IP del servidor
        int puerto = 6000; // Puerto del servidor

        try (Socket cliente = new Socket(host, puerto)) {
            System.out.println("Conectado al servidor.");
            System.out.println("Puerto local (cliente): " + cliente.getLocalPort());
            System.out.println("Puerto remoto (servidor): " + cliente.getPort());

            // Obtenemos el canal de entrada
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            // Obtenemos el canal de salida
            PrintWriter salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream())), true);

            Scanner sc = new Scanner(System.in);

            String linea;

            // El programa cliente no analiza los mensajes enviados por el usuario, simplemente los reenvía al servidor hasta que este se despide con "Adios"
            try {
                while (true) {
                    // Leo la entrada del usuario
                    System.out.println("Escribe algo para enviar al servidor: ");
                    linea = sc.nextLine();
                    // La envia al servidor por el OutputStream
                    salida.println(linea);
                    // Recibe la respuesta del servidor por el InputStream
                    linea = entrada.readLine();
                    // Envía a la salida estándar la respuesta del servidor
                    System.out.println("Respuesta servidor: " + linea);
                    // Si es "Adios" es que finaliza la comunicación
                    if (linea.equals("Adios")) {
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        }
    }
}
