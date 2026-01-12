package socketTCPpalabra;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        String direccionServidor = "localhost";
        int puertoServidor = 3000;

        Socket socketCliente = null;
        DataInputStream entradaDelServidor = null;
        DataOutputStream salidaAlServidor = null;
        Scanner sc = new Scanner(System.in);

        try {
            socketCliente = new Socket(direccionServidor,puertoServidor);

            entradaDelServidor = new DataInputStream(socketCliente.getInputStream());
            salidaAlServidor = new DataOutputStream(socketCliente.getOutputStream());

            String palabra = entradaDelServidor.readUTF();
            while(!palabra.equalsIgnoreCase("fin")){

                System.out.println("Debes traducir la palabra: " + palabra);
                String respuesta = sc.nextLine();
                salidaAlServidor.writeUTF(respuesta);
                palabra = entradaDelServidor.readUTF();
            }

            System.out.println("Ya no hay que traducir más palabras.");
            entradaDelServidor.close();
            salidaAlServidor.close();
            socketCliente.close();
            sc.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
