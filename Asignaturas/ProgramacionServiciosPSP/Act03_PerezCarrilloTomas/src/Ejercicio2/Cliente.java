package Ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        String direccionServidor = "localhost";
        int puertoServidor = 2900;

        Socket socketCliente = null;
        DataInputStream servidorEntrada = null;
        DataOutputStream servidorSalida = null;
        Scanner sc = new Scanner(System.in);

        try {
            socketCliente = new Socket(direccionServidor,puertoServidor);

            servidorEntrada = new DataInputStream(socketCliente.getInputStream());
            servidorSalida = new DataOutputStream(socketCliente.getOutputStream());

            int numUsuario;

            boolean acierto = false;

            String inicioJuego = servidorEntrada.readUTF();
            System.out.println(inicioJuego);

            while(!acierto){
                System.out.println("Cliente, introduce un número: ");
                numUsuario = solicitarNumUsuario(sc);
                servidorSalida.writeInt(numUsuario);

                int respuestaServer = servidorEntrada.readInt();

                if(respuestaServer == 1){
                    System.out.println("Te has pasado (El secreto es menor a " + numUsuario + ")");
                }
                else if(respuestaServer == 2){
                    System.out.println("Te has quedado corto (El secreto es mayor a " + numUsuario + ")");
                }
                else if(respuestaServer == 3){
                    System.out.println("Enhorabuena, has acertado el número secreto.");
                    acierto = true;
                }
            }
            servidorEntrada.close();
            servidorSalida.close();
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
