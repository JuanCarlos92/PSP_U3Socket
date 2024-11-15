package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class B_MiPrimerCliente {
    public static void main(String[] args) throws IOException {

        String host = "localhost";
        int puerto = 6000;

        Socket cliente = new Socket(host, puerto);

        //Mostrar informacion de conexion
        System.out.println("Conectado al servidor: ");
        System.out.println("Puerto local (cliente) : " +cliente. getLocalPort());
        System.out.println("Puerto remoto (servidor): " + cliente.getPort());
        System.out.println("Direccion Ip del servidor: " + cliente.getInetAddress().getHostAddress());

        cliente.close();
        System.out.println("Cliente desconectado.");
    }
}
