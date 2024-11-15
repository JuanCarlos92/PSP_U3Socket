package org.example.UDP;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteVideo {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress servidorIP = InetAddress.getByName("localhost");
        int puertoServidor = 4000;

        File video = new File("src/main/resources/video.mp4");

        FileInputStream fileinputstream = new FileInputStream(video);

        byte[] buffer = new byte[1024];
        int bytesLeidos;

        System.out.println("Enviando el video...");
        while ((bytesLeidos = fileinputstream.read(buffer)) != -1) {

            DatagramPacket paquete = new DatagramPacket(buffer, bytesLeidos, servidorIP, puertoServidor);
            socket.send(paquete);
        }

        String fin = "Enviado";
        byte[] finBuffer = fin.getBytes();
        DatagramPacket paqueteFin = new DatagramPacket(finBuffer, finBuffer.length, servidorIP, puertoServidor);
        socket.send(paqueteFin);

        System.out.println("Video enviado completamente.");
    }
}
