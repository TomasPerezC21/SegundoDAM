package RA3.Ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteEj2 {

    public static void main(String[] args) {

        int puertoServidor = 5555;
        String direccionServer = "localhost";

        Socket socketCliente = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        Scanner sc = new Scanner(System.in);


        try {
            socketCliente = new Socket(direccionServer,puertoServidor);

            dis = new DataInputStream(socketCliente.getInputStream());
            dos = new DataOutputStream(socketCliente.getOutputStream());

            boolean seguir = true;

            while (seguir) {
                String feedbackServer = dis.readUTF();
                System.out.println("Servidor: " + feedbackServer);

                // Si el mensaje es el final, cerramos
                if (feedbackServer.equalsIgnoreCase("FIN")) {
                    System.out.println("Juego terminado.");
                    seguir = false;
                }
                // Si el mensaje NO contiene palabras clave de "paso de turno", pedimos número
                else if (!feedbackServer.startsWith("CORRECTO") &&
                        !feedbackServer.contains("superado") &&
                        !feedbackServer.contains("agotados")) {

                    System.out.print("Tu número: ");
                    int respuesta = sc.nextInt();
                    dos.writeInt(respuesta);
                    dos.flush();
                }
            }



            dis.close();
            dos.close();
            socketCliente.close();
            sc.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }


}
