package Ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class GestorClientes implements Runnable{

    private Socket socketCliente;
    private int idCliente;
    private static int NUMCLIENTE = 0;

    private int secretoServidor;

    private final static Object candado = new Object();

    public GestorClientes(Socket socketCliente,  int secretoServidor) {
        this.socketCliente = socketCliente;
        synchronized (candado){
            NUMCLIENTE++;
            this.idCliente = NUMCLIENTE;

        }
        this.secretoServidor = secretoServidor;
    }

    @Override
    public void run() {

        DataInputStream clienteEntrada = null;
        DataOutputStream clienteSalida = null;

        try {
            clienteEntrada = new DataInputStream(socketCliente.getInputStream());
            clienteSalida = new DataOutputStream(socketCliente.getOutputStream());

            boolean acierto = false;

            int numCliente;

            clienteSalida.writeUTF("El cliente con id " + idCliente + " empieza a jugar.");

            while (!acierto){
                numCliente = clienteEntrada.readInt();

                System.out.println("El  cliente " + idCliente + " dice: " + numCliente);
                if (numCliente > secretoServidor) {
                    clienteSalida.writeInt(1);
                } else if (numCliente < secretoServidor) {
                    clienteSalida.writeInt(2);
                } else {
                    clienteSalida.writeInt(3);
                    acierto = true;
                }
            }

            System.out.println("El juego ha terminado para el jugador: "+idCliente+" .");
            clienteEntrada.close();
            clienteSalida.close();
            socketCliente.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
