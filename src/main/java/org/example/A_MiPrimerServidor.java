package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class A_MiPrimerServidor {
    public static void main(String[] args) throws IOException {
        int puerto = 6000;

        ServerSocket servidor = new ServerSocket(puerto);


        Socket cliente1 = servidor.accept();
        System.out.println("Conexion con el " + cliente1);
        Socket cliente2 = servidor.accept();
        System.out.println("Conexion con el " + cliente2);
        Socket cliente3 = servidor.accept();
        System.out.println("Conexion con el " + cliente3);


    }
}
