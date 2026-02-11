package RA3.EjercicioSubastas;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {

        int puertoServidor = 5555;


        ServerSocket socketServidor = null;
        Socket socketCliente = null;

        try {
            socketServidor = new ServerSocket(puertoServidor);

            while (true) {
                socketCliente = socketServidor.accept();

                Gestor g = new Gestor(socketCliente);
                new Thread(g).start();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
