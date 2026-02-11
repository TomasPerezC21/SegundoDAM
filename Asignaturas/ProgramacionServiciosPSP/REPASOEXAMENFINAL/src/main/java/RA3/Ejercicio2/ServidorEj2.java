package RA3.Ejercicio2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorEj2 {

    public static void main(String[] args) {

        int puertoServidor = 5555;
        ServerSocket socketServidor = null;
        Socket socketCliente = null;
        String fichero = "src/main/java/RA3/Ejercicio2/ficheroEj2";

        try {
           socketServidor = new ServerSocket(puertoServidor);

           ArrayList<String> datos = obtenerDatosFichero(fichero);

            while (true) {
                    socketCliente = socketServidor.accept();
                new Thread(new GestorClientes(socketCliente, datos)).start();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static ArrayList<String> obtenerDatosFichero(String fichero) throws IOException {

        ArrayList<String> datosFichero = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(fichero));
        String linea;
        while ((linea = br.readLine())!=null) {
            datosFichero.add(linea);
        }

        return datosFichero;
    }


}
