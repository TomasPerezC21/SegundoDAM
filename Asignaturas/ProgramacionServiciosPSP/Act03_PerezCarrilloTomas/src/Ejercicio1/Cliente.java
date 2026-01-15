package Ejercicio1;

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



            System.out.println("Cliente, introduce un número: ");
            int numUsuario = Integer.MAX_VALUE;


            while(numUsuario!=numeroSecretoServer){
                numUsuario = solicitarNumUsuario(sc);
                salidaAlServidor.writeInt(numUsuario);
                String respuestaServer = entradaDelServidor.readUTF();
                System.out.println(respuestaServer);
            }

            entradaDelServidor.close();
            salidaAlServidor.close();
            socketCliente.close();
            sc.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static int solicitarNumUsuario(Scanner sc){
        return sc.nextInt();
    }


}
