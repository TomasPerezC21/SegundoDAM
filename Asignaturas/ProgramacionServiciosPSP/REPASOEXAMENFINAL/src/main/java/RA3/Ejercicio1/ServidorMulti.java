package RA3.Ejercicio1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.Random;

public class ServidorMulti {

    public static void main(String[] args) {
        Random aleatorio = new Random();
        int puertoServidor = 1900;
        ServerSocket socket = null;
        Socket socketCliente = null;

        try {
            socket = new ServerSocket(puertoServidor);

            int numSecreto = aleatorio.nextInt(0,501);

            while (true) {
                socketCliente = socket.accept();
                GestorClientes gc = new GestorClientes(socketCliente, numSecreto);
                new Thread(gc).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
