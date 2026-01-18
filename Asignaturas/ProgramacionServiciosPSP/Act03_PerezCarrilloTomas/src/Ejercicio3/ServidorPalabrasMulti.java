package Ejercicio3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class ServidorPalabrasMulti {

    private static String RUTAFICHERO = "src/Ejercicio3/PalabrasTraducir";

    public static void main(String[] args) {

        int puertoServidor = 2500;
        ServerSocket socket = null;
        Socket socketCliente = null;

        ArrayList<String> datosFichero = obtenerDatosFichero();

        try {
            socket = new ServerSocket(puertoServidor);

            while (true) {
                //Cada cliente recibe las palabras del fichero en diferente orden
                Collections.shuffle(datosFichero);

                socketCliente = socket.accept();
                GestorPalabras gp = new GestorPalabras(socketCliente, datosFichero);
                new Thread(gp).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static ArrayList<String> obtenerDatosFichero(){
        ArrayList<String> palabras = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(RUTAFICHERO));

            String linea;

            while ((linea = br.readLine()) != null) {
                palabras.add(linea);
            }

            Collections.shuffle(palabras);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return palabras;
    }
}
