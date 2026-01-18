package Ejercicio2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServidorMulti {

    public static void main(String[] args) {

        Random aleatorio = new Random();

        int puertoServidor = 2900;
        ServerSocket socket = null;
        Socket socketCliente = null;

        int secreto = aleatorio.nextInt(0,201);

        try {
            socket = new ServerSocket(puertoServidor);

            while(true){
                socketCliente = socket.accept();
                GestorClientes gp = new GestorClientes(socketCliente, secreto);
                new Thread(gp).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
