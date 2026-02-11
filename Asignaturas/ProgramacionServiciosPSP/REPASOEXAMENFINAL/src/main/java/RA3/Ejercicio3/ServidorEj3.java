package RA3.Ejercicio3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class ServidorEj3 {

    public static void main(String[] args) {

        int puerto = 2222;
        ServerSocket socketServer = null;
        Socket socketCliente = null;
        String fichero = "src/main/java/RA3/Ejercicio3/numeros";

        try {
            socketServer = new ServerSocket(puerto);

            ArrayList<Integer> datosFichero = obtenerDatosFichero(fichero);

            while(true){
                socketCliente = socketServer.accept();

                Collections.shuffle(datosFichero);

                Gestor g = new Gestor(socketCliente,datosFichero);
                new Thread(g).start();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static ArrayList<Integer> obtenerDatosFichero(String fichero) throws IOException {

        ArrayList<Integer> datos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(fichero));
        String linea;
        while ((linea = br.readLine()) != null) {
            datos.add(Integer.parseInt(linea));
        }
        return datos;




    }


}
