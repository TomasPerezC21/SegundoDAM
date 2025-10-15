package Ejercicio4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App2 {

    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("Falta el nombre del fichero.");
            System.exit(1);
        }
        String nombreFichero = args[0];

        try {
            FileReader fr = new FileReader(nombreFichero);
            BufferedReader br = new BufferedReader(fr);

            //Variable auxiliar con el número mínimo posible (investigación)
            int tempAux = Integer.MIN_VALUE;

            String lineaPrint = null;

            String linea = br.readLine();
            while (linea != null) {
                String[] temp =  linea.split(" Temperatura: ");
                int temperatura = Integer.parseInt(temp[1].trim());
                if (temperatura > tempAux) {
                    tempAux = temperatura;
                    lineaPrint = linea;
                }
                linea = br.readLine();
            }

            if (lineaPrint != null) {
                //Salida que se recibe en la app3
                System.out.println(lineaPrint);
            }else{
                System.out.println("El fichero no tiene datos.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
