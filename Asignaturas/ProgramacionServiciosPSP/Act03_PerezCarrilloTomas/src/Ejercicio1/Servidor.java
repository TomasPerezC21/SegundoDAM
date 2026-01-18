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
        Random rand = new Random();
        int puertoServidor = 1900;


        System.out.println("GENERANDO NÚMERO SECRETO EN SERVIDOR...");
        int secreto = generarNumeroSecreto(rand);

        ServerSocket socketServidor = null;
        Socket socketCliente = null;
        DataInputStream clienteEntrada = null;
        DataOutputStream clienteSalida = null;

        try{
            socketServidor = new ServerSocket(puertoServidor);
            socketCliente = socketServidor.accept();
            clienteEntrada = new DataInputStream(socketCliente.getInputStream());
            clienteSalida = new DataOutputStream(socketCliente.getOutputStream());

            int numeroCliente;

            boolean acierto = false;

            while(!acierto){
                numeroCliente = clienteEntrada.readInt();
                if (numeroCliente > secreto){
                    clienteSalida.writeInt(1);
                }
               else if(numeroCliente < secreto){
                    clienteSalida.writeInt(2);
                }
                else {
                    clienteSalida.writeInt(3);
                    acierto = true;
                }
            }

        }catch(IOException e){
            System.err.println("Error en servidor");
        }

        System.out.println("Juego terminado. Número secreto: " + secreto);
    }

    private static int generarNumeroSecreto(Random rand) {
        int numeroSecreto = rand.nextInt(0,201);
        return numeroSecreto;
    }




}
