package RA3.Ejercicio3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteEj3 {

    public static void main(String[] args) {

        int puerto = 2222;
        String servidor = "localhost";


        Socket socketCliente = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        Scanner sc = new Scanner(System.in);

        try {
            socketCliente = new Socket(servidor, puerto);

            dis = new DataInputStream(socketCliente.getInputStream());
            dos = new DataOutputStream(socketCliente.getOutputStream());

            int iteraciones = dis.readInt();

            for (int i = 0; i < iteraciones; i++) {
                String feedbackServer = dis.readUTF();
                System.out.println("Servidor: " + feedbackServer);

                dos.writeUTF(sc.nextLine());
                dos.flush();

                System.out.println("Servidor: " + dis.readUTF());
            }

            System.out.println("Servidor: " + dis.readUTF());

            dos.close();
            dis.close();
            socketCliente.close();



        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
