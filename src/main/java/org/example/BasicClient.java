package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BasicClient {

    public static void main(String[] args) throws IOException {
        Socket socketCliente = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;

        // Creamos un socket en el lado cliente, enlazado con un servidor que está en
        // la misma máquina que el cliente y que escucha en el puerto 4444
        try {
            socketCliente = new Socket("localhost", 4444);
            // Obtenemos el canal de entrada
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            // Obtenemos el canal de salida
            salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);
        } catch (IOException e) {
            System.err.println("No puede establecer canales de E/S para la conexión");
        }
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
