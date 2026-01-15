package Ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int puertoServidor = 1900;

        System.out.println("GENERANDO NÚMERO SECRETO EN SERVIDOR...");
        int secreto = generarNumeroSecreto(rand);

        ServerSocket socketServidor = null;
        Socket socketCliente = null;
        DataInputStream entradaDatosDelCliente = null;
        DataOutputStream salidaDatosAlCliente = null;

        try{

            socketServidor = new ServerSocket(puertoServidor);
            socketCliente = socketServidor.accept();
            entradaDatosDelCliente = new DataInputStream(socketCliente.getInputStream());
            salidaDatosAlCliente = new DataOutputStream(socketCliente.getOutputStream());

            salidaDatosAlCliente.writeInt(secreto);

            int numeroCliente = Integer.MAX_VALUE;



            while(numeroCliente!=secreto){
                numeroCliente = entradaDatosDelCliente.readInt();
                if (numeroCliente > secreto){
                    salidaDatosAlCliente.writeUTF("El número del cliente es mayor al número secreto.");
                }
               else if(numeroCliente < secreto){
                    salidaDatosAlCliente.writeUTF("El número del cliente es menor al número secreto.");
                }
                else {
                    salidaDatosAlCliente.writeUTF("El cliente ha acertado el número secreto: " + secreto);
                }
            }

        }catch(IOException e){
            System.err.println("Error en servidor");
        }

    }

    private static int generarNumeroSecreto(Random rand) {
        int numeroSecreto = rand.nextInt(0,201);
        return numeroSecreto;
    }




}
