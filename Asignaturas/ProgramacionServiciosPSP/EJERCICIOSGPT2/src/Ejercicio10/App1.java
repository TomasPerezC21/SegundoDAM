package Ejercicio10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App1 {

    public static void main(String[] args) {

        String nombreFichero = args[0];
        String palabraApp2 = args[1];

        int contadorPalabraApp2 = 0;

        try {
            FileReader fr = new FileReader(nombreFichero);
            BufferedReader br = new BufferedReader(fr);
            String linea =  br.readLine();
            while (linea != null) {
                if (linea.contains(palabraApp2) && linea.contains(palabraApp2+".")) {
                    contadorPalabraApp2++;
                }
                linea = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("La palabra " + palabraApp2 + " aparece " + contadorPalabraApp2 + " veces.");

    }

}
