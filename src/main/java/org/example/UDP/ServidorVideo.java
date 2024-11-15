package org.example.UDP;

import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorVideo {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(4000);
        System.out.println("Esperando el video...");

        FileOutputStream fileoutputstream = new FileOutputStream("src/main/resources/recibido/video.mp4");
        byte[] buffer = new byte[1024];

        while (true) {
            DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
            socket.receive(paqueteRecibido);

            String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
            if (mensaje.equals("Enviado")) {
                System.out.println("El video se ha recibido en el servidor.");
                break;
            }

            fileoutputstream.write(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
        }

        System.out.println("Archivo de video guardado correctamente.");
        fileoutputstream.close();
        socket.close();

    }
}
