package RA3.Ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorClientes implements Runnable {

    private Socket socketCliente;
    private int idCliente;
    private static final Object candado = new Object();
    private static int NUMCLIENTE = 0;
    private ArrayList<String> datos;


    public GestorClientes(Socket socketCliente, ArrayList<String> datos) {
        this.socketCliente = socketCliente;
        this.datos = datos;
        synchronized (candado) {
            NUMCLIENTE++;
            this.idCliente = NUMCLIENTE;
        }

    }

    @Override
    public void run() {
        DataInputStream dis = null;
        DataOutputStream dos = null;

        try {

            dis = new DataInputStream(socketCliente.getInputStream());
            dos = new DataOutputStream(socketCliente.getOutputStream());

            int aciertosResumen = 0;

            for (String dato: datos){
                String[] partes = dato.split(" ");
                int objetivo = Integer.parseInt(partes[0]);
                int intentosMax = Integer.parseInt(partes[1]);

                boolean acertado = false;

                String enviarCliente = "El cliente " + idCliente + " empieza a jugar. Tienes " + intentosMax + " intentos para adivinar el num oculto. \n numero oculto: " + objetivo;


                while(intentosMax != 0 && !acertado) {

                    dos.writeUTF(enviarCliente);
                    dos.flush();

                    int numeroCliente = dis.readInt();

                    if (numeroCliente > objetivo) {
                        intentosMax--;
                        dos.writeUTF("El número oculto es menor al introducido.");
                    }else if (numeroCliente < objetivo) {
                        dos.writeUTF("El número oculto es mayor al introducido.");
                        intentosMax--;
                    }else{
                        dos.writeUTF("CORRECTO");
                        aciertosResumen++;
                        acertado = true;
                    }

                    if (intentosMax == 0) {
                        dos.writeUTF("Intentos agotados.");
                    }

                dos.flush();
                }
            }

            dos.writeUTF("El cliente ha superado " + aciertosResumen + " de " + datos.size());
            dos.writeUTF("FIN");
            dos.flush();

            dis.close();
            dos.close();
            socketCliente.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
