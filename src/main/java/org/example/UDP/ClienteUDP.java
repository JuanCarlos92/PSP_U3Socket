package org.example.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress servidorIP = InetAddress.getByName("localhost");
        int puertoServidor = 9876;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Escribe un mensaje para enviar al servidor: ");
        String mensaje = scanner.nextLine();
        byte[] bufferMensaje = mensaje.getBytes();
        // Enviar mensaje al servidor
        DatagramPacket paqueteEnvio = new DatagramPacket(bufferMensaje, bufferMensaje.length, servidorIP, puertoServidor);
        socket.send(paqueteEnvio);
        System.out.println("Información del paquete enviado: " + socket.getLocalPort());
        System.out.println("Mensaje enviado al servidor.");

        // Esperar respuesta del servidor
        byte[] bufferRespuesta = new byte[1024];
        DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);
        socket.receive(paqueteRespuesta);
        String respuesta = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength());
        System.out.println("Respuesta del servidor: " + respuesta);

        //socket.close();
        //scanner.close();
    }
}
