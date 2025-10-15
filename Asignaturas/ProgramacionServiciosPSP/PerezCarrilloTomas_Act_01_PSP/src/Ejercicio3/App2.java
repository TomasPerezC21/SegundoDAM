package Ejercicio3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class App2 {

    public static final String NOMBRE_FICHERO = "Ejercicio3.txt";

    public static void main(String[] args) {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);


        System.out.println("¿Cuántas veces quieres llamar al hijo?" + System.lineSeparator()
                + "Escribe un número entre el 4 y el 6(incluidos): ");

        int cantidad = sc.nextInt();

        while (cantidad < 4 || cantidad > 6) {
            System.out.println("Tiene que ser entre el 4 y el 6 (incluidos): ");
            cantidad = sc.nextInt();
        }

        String rutaArchivo = "src/Ejercicio3/App1.java";

        for (int i = 0; i < cantidad; i++) {
            String modo;

            if (i == 0) {
                modo = "1";
            }
            else {
                modo = "2";
            }

            ProcessBuilder pb = new ProcessBuilder("java", rutaArchivo, NOMBRE_FICHERO, modo);

            try{
                Process p = pb.start();

                int segundos = r.nextInt(4) + 2;

                System.out.println("Llamada: " + (i + 1) + ". Ha tardado: " + segundos + " segundos.");

                p.waitFor();

                if(i < cantidad - 1){
                    Thread.sleep(segundos * 1000L);
                }

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        System.out.println("Contenido del fichero: ");
        try{
            BufferedReader br = new BufferedReader(new FileReader(NOMBRE_FICHERO));
            String linea = br.readLine();
            while (linea != null){
                System.out.println(linea);
                linea = br.readLine();
            }
            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }






    }
}
