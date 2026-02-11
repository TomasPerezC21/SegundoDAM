package RA3.EjercicioSubastas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Gestor implements Runnable{

    private Socket socketCliente;
    private int idCliente;

    private static final Object candado = new Object();

    private static int NUMCLIENTE = 0;
    private static int precioGanador = 100;
    private static String ganadorActual = "nadie";

    public Gestor(Socket socketCliente) {
        this.socketCliente = socketCliente;

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

            String mensajeInicial = "Servidor: Jugador " + this.idCliente + " se ha conectado.";
            dos.writeUTF(mensajeInicial);
            dos.flush();

            String pedirNombre = "Introduce tu nombre: ";
            dos.writeUTF(pedirNombre);
            dos.flush();

            String nombreJugador = dis.readUTF();

            boolean seguir = true;

            while(seguir){

                String estadoPuja = "Servidor: La puja por el producto (PS5) va por: " + precioGanador + ".";
                dos.writeUTF(estadoPuja);
                dos.flush();

                dos.writeUTF("Introduce tu puja (0 para parar de pujar):");
                dos.flush();

                int pujaCliente = dis.readInt();

                if(pujaCliente <= 0){
                    seguir = false;
                    dos.writeUTF("Ha terminado la puja");
                    dos.flush();
                    dis.close();
                    dos.close();
                }else{
                    String respuesta;

                    //Zona critica
                    synchronized (candado) {
                        if (pujaCliente > precioGanador) {
                            precioGanador = pujaCliente;
                            ganadorActual = nombreJugador;
                            respuesta = "ACEPTADA. ¡Ahora vas ganando!";
                            System.out.println("[SERVIDOR] Nuevo líder: " + ganadorActual + " con " + precioGanador + "€");
                        } else {
                            respuesta = "RECHAZADA. Alguien ha pujado más o igual (" + precioGanador + "€).";
                        }
                    }
                    dos.writeUTF(respuesta);
                }
                dos.flush();

            }

            System.out.println("El ganador de la PS5 es: " + ganadorActual);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
