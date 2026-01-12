package socketTCPpalabra;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Servidor {


    public static void main(String[] args) {

        ArrayList<String> palabras = new ArrayList<String>(
                List.of("Perro", "Casa", "Amarillo", "Avión", "Fin", "Telefono", "Rojo", "Ventilador")
        );

        int puertoServidor = 3000;
        ServerSocket socket = null;
        Socket socketCliente = null;
        DataInputStream entradaDatosDelCliente = null;
        DataOutputStream salidaDatosAlCliente = null;

        try {
            socket = new ServerSocket(puertoServidor);
            socketCliente = socket.accept();
            entradaDatosDelCliente = new DataInputStream(socketCliente.getInputStream());
            salidaDatosAlCliente = new DataOutputStream(socketCliente.getOutputStream());

            Collections.shuffle(palabras);
            String palabra = palabras.removeFirst();
            salidaDatosAlCliente.writeUTF(palabra);

            while(!palabra.equalsIgnoreCase("fin")) {
                System.out.println("Le he enviado al jugador la palabra: "+palabra);
                String respuestaCliente = entradaDatosDelCliente.readUTF();
                System.out.println("El cliente me devuelve: "+respuestaCliente);
                palabra = palabras.removeFirst();
                salidaDatosAlCliente.writeUTF(palabra);
            }

            System.out.println("El juego ha terminado.");
            entradaDatosDelCliente.close();
            salidaDatosAlCliente.close();
            socketCliente.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Error en Servidor");
        }




    }


}
