package socketTPCpalabrasMultihilo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorPalabras implements Runnable{

    private Socket socketCliente;
    private int idCliente;

    private static int NUMCLIENTE = 0;
    private final Object candado = new Object();


    public GestorPalabras(Socket socketCliente){

        this.socketCliente = socketCliente;
        synchronized (candado){
            NUMCLIENTE++;
            this.idCliente = NUMCLIENTE;
        }
    }

    @Override
    public void run() {
        ArrayList<String> palabras = new ArrayList<String>(
                List.of("Perro", "Casa", "Amarillo", "Avión", "Fin", "Telefono", "Rojo", "Ventilador")
        );

        DataInputStream entradaDatosDelCliente = null;
        DataOutputStream salidaDatosAlCliente = null;

        try {
            entradaDatosDelCliente = new DataInputStream(socketCliente.getInputStream());
            salidaDatosAlCliente = new DataOutputStream(socketCliente.getOutputStream());

            Collections.shuffle(palabras);
            String palabra = palabras.removeFirst();
            salidaDatosAlCliente.writeUTF(palabra);

            while(!palabra.equalsIgnoreCase("fin")) {
                System.out.println("Le he enviado al jugador: "+ idCliente +" la palabra: "+palabra);
                String respuestaCliente = entradaDatosDelCliente.readUTF();
                System.out.println("El cliente numero: "+ idCliente +" me devuelve: "+respuestaCliente);
                palabra = palabras.removeFirst();
                salidaDatosAlCliente.writeUTF(palabra);
            }

            System.out.println("El juego ha terminado para el jugador: "+idCliente+" .");
            entradaDatosDelCliente.close();
            salidaDatosAlCliente.close();
            socketCliente.close();

        } catch (IOException e) {
            System.err.println("Error en Servidor");
        }

    }
}
