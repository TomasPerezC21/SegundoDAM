package RA3.Ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GestorClientes implements Runnable {

    private Socket socketCliente;
    private int idCliente;
    private int numSecreto;
    private final Object candado = new Object();

    private static int NUMCLIENTE;

    public GestorClientes(Socket socketCliente, int numSecreto) {
        this.socketCliente = socketCliente;
        this.idCliente = idCliente;
        this.numSecreto = numSecreto;

        synchronized (candado) {
            NUMCLIENTE++;
            this.idCliente = NUMCLIENTE;
        }

    }

    @Override
    public void run() {

        DataInputStream entradaDelCliente = null;
        DataOutputStream salidaAlCliente = null;

        int contadorIntentos = 0;

        try {
            entradaDelCliente = new DataInputStream(socketCliente.getInputStream());
            salidaAlCliente = new DataOutputStream(socketCliente.getOutputStream());


            boolean continuar = true;

            while (continuar) {

                int numRecibido = entradaDelCliente.readInt();
                contadorIntentos++;

                if (numRecibido < numSecreto) {
                    salidaAlCliente.writeUTF("MAYOR");
                }else if (numRecibido > numSecreto) {
                    salidaAlCliente.writeUTF("MENOR");
                }else{
                    salidaAlCliente.writeUTF("CORRECTO");
                    salidaAlCliente.writeInt(contadorIntentos);
                    continuar = false;
                }

                salidaAlCliente.flush();
            }


            entradaDelCliente.close();
            salidaAlCliente.close();
            socketCliente.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
