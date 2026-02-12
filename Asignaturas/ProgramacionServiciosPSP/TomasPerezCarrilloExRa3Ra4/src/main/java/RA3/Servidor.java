package RA3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {


        int puerto = 5555;
        ServerSocket socketServer = null;
        Socket socketCliente = null;

        try {

            socketServer = new ServerSocket(puerto);

            while (true) {
                socketCliente = socketServer.accept();
                Gestor g = new  Gestor(socketCliente);
                new Thread(g).start();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
