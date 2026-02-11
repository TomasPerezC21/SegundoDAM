package RA3.Ejercicio3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Gestor implements Runnable {

    private Socket socketCliente;
    private int idCliente;
    private static final Object candado = new Object();
    private static int NUMCLIENTE = 0;
    private ArrayList<Integer> datos;

    public Gestor(Socket socketCliente, ArrayList<Integer> datos) {
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

            int aciertos = 0;
            int total = datos.size();

            dos.writeInt(total);
            dos.flush();

            while (!datos.isEmpty()) {

               int dato = datos.removeFirst();

               boolean parServidor = dato % 2 == 0;

               dos.writeUTF(dato + " par o impar: ");

               String respuestaCliente = dis.readUTF();
               String resultado;

               if (respuestaCliente.equalsIgnoreCase("par") && parServidor) {
                   aciertos++;
                  resultado = "CORRECTO";

               }else if (respuestaCliente.equalsIgnoreCase("par") && !parServidor) {
                   resultado = "INCORRECTO";
               }else if (respuestaCliente.equalsIgnoreCase("impar") && parServidor) {
                   resultado = "INCORRECTO";
               }else{
                   aciertos++;
                   resultado = "CORRECTO";
               }

               dos.writeUTF(resultado);
               dos.flush();

            }

            dos.writeUTF("RESUMEN: " + aciertos + " aciertos de " +total);

            dis.close();
            dos.close();
            socketCliente.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
