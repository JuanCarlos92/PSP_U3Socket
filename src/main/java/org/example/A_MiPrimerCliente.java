package org.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class A_MiPrimerCliente {
    public static void main(String[] args) throws IOException {

        String host = "localhost";
        int puerto = 6000;

        Socket cliente = new Socket(host, puerto);

        InetAddress i = cliente. getInetAddress();
        System.out.println("Puerto local: " +cliente. getLocalPort());
        System.out.println("Puerto remoto: " + cliente.getPort());
        System.out.println("Nombre host/IP: " + cliente.getInetAddress());
        System.out.println("Host remoto: " + i.getHostAddress().toString());
        System.out.println("IP host Remoto: " + i.getHostAddress().toString());

        cliente.close();
    }
}
