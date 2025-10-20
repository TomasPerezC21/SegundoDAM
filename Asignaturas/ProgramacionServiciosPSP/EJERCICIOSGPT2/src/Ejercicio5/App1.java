package Ejercicio5;

import java.io.*;

public class App1 {

    public static void main(String[] args) {

        String palabra = args[0];

        String nombreFichero = "ejercicio5.txt";

        File fichero = new File(nombreFichero);
        try {
            FileWriter fw = new FileWriter(fichero, true);
            fw.write(palabra + " ");
            fw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }


}
