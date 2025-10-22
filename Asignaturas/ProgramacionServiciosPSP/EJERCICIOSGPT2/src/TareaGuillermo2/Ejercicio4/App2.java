package TareaGuillermo2.Ejercicio4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class App2 {

    public static void main(String[] args) {

        String nombreFichero = args[0];
        String lineaPrint;

        try {
            FileReader fr = new FileReader(nombreFichero);
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            lineaPrint = linea;
            String[] aux = linea.split(" ");
            int tempMax = Integer.parseInt(aux[2]);

            while (linea != null) {
                String[] aux2 = linea.split(" ");
                int temp = Integer.parseInt(aux2[2]);
                if (temp > tempMax) {
                    lineaPrint = linea;
                }
                linea = br.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("Día con temperatura más alta: " + lineaPrint);

    }

}
