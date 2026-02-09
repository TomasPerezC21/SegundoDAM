package RA3.Ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        String direccionServidor = "localhost";
        int puertoServidor = 1900;

        Socket socketCliente = null;
        DataInputStream entradaDelServidor = null;
        DataOutputStream salidaAlServidor = null;
        Scanner sc = new Scanner(System.in);

        try {
            socketCliente = new Socket(direccionServidor,puertoServidor);

            entradaDelServidor = new DataInputStream(socketCliente.getInputStream());
            salidaAlServidor = new DataOutputStream(socketCliente.getOutputStream());

            System.out.println("Adivina el número (0-500)");
            boolean terminado = false;

            while (!terminado) {
                System.out.print("Introduce un número: ");
                int miNum = sc.nextInt();
                salidaAlServidor.writeInt(miNum);

                String respuesta = entradaDelServidor.readUTF();

                if (respuesta.equals("CORRECTO")) {
                    int intentos = entradaDelServidor.readInt();
                    System.out.println("ACIERTO. Lo lograste en " + intentos + " intentos.");
                    terminado = true;
                } else {
                    System.out.println("El número secreto es " + respuesta);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
