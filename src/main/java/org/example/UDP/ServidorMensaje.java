package org.example.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class ServidorMensaje {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(4000);
        System.out.println("Esperando mensajes en el puerto 4000...");

        while (true){
            byte[] buffer = new byte[4000];
            DatagramPacket paqueteDelCliente = new DatagramPacket(buffer, buffer.length);
            socket.receive(paqueteDelCliente);

            String mensaje = new String(paqueteDelCliente.getData(), 0, paqueteDelCliente.getLength());
            System.out.println("Mensaje recibido del cliente: " + mensaje);

            // Respuesta al cliente
            String respuesta = "Mensaje recibido: " + mensaje;
            byte[] bufferRespuesta = respuesta.getBytes();
            DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length, paqueteDelCliente.getAddress(), paqueteDelCliente.getPort());
            System.out.println("Info del paquete: " + socket);
            socket.send(paqueteRespuesta);  // Enviar respuesta
            System.out.println("Respuesta enviada al cliente.");

        }
    }
}
