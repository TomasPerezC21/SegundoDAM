package TareaGuillermo2.Ejercicio3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class App2 {

    public static final String NOMBRE_FICHERO ="Ejercicio3";

    public static void main(String[] args) {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce un numero del 4 al 6 (ambos incluidos): ");
        int num =  sc.nextInt();

        while (num < 4 || num > 6) {
            System.out.println("Tiene que ser entre el 4 y el 6 (ambos incluidos): ");
            num =  sc.nextInt();
        }

        String rutaApp1 = "src/TareaGuillermo2/Ejercicio3/App1.java";



        for (int i = 0; i < num; i++) {
            String modo;
            if (i == 0){
                modo = "1";
            }
            else{
                modo = "2";
            }

            ProcessBuilder pb = new ProcessBuilder("java", rutaApp1, NOMBRE_FICHERO, modo);
            pb.inheritIO();

            int espera = r.nextInt(2, 6);
            try {
                Process p = pb.start();
                p.waitFor();
                Thread.sleep(1000L *espera);

            } catch (Exception e) {
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
