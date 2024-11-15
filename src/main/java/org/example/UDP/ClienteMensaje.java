package org.example.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteMensaje {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress servidorIP = InetAddress.getByName("localhost");
        int puertoServidor = 4000;

        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe un mensaje para el servidor: ");
        String msg = sc.nextLine();

        byte[] bufferMensaje = msg.getBytes();
        DatagramPacket paqueteEnviado = new DatagramPacket(bufferMensaje, bufferMensaje.length, servidorIP, puertoServidor);
        socket.send(paqueteEnviado);

        System.out.println("Información del paquete enviado: " + socket.getLocalPort());
        System.out.println("Mensaje enviado al servidor.");

        // Esperar respuesta del servidor
        byte[] bufferRespuesta = new byte[1024];
        DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);
        socket.receive(paqueteRespuesta);
        String respuesta = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength());
        System.out.println("Respuesta del servidor: " + respuesta);
    }
}
