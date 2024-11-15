package org.example.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9876);
        System.out.println("Servidor UDP esperando mensajes en el puerto 9876...");

        while (true) {

            // Buffer para recibir datos
            byte[] buffer = new byte[1024];
            DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
            socket.receive(paqueteRecibido);  // Espera recibir un paquete
            // Muestra el mensaje recibido
            String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
            System.out.println("Mensaje recibido del cliente: " + mensaje);

            // Respuesta al cliente
            String respuesta = "Mensaje recibido: " + mensaje;
            byte[] bufferRespuesta = respuesta.getBytes();
            DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length, paqueteRecibido.getAddress(), paqueteRecibido.getPort());
            System.out.println("Info del paquete: " + socket);
            socket.send(paqueteRespuesta);  // Enviar respuesta
            System.out.println("Respuesta enviada al cliente.");

        }
    }
}
