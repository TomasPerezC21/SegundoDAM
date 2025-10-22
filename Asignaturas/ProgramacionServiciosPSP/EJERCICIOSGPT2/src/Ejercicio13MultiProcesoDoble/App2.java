package Ejercicio13MultiProcesoDoble;

import java.io.IOException;
import java.util.Random;

public class App2 {

    public static void main(String[] args) {

        Random r =  new Random();

        int numLlamadas = r.nextInt(1, 20);

        String rutaApp1 = "src/Ejercicio13MultiProcesoDoble/App1.java";


        for (int i = 0 ; i < numLlamadas ; i++) {
            int numero = r.nextInt(1, 40);

            ProcessBuilder pb = new ProcessBuilder("java", rutaApp1, String.valueOf(numero));
            pb.inheritIO();
            try {
                Process p = pb.start();
                p.waitFor();
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
