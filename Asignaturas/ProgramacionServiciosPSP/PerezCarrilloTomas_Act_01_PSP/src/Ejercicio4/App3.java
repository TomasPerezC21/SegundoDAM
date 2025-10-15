package Ejercicio4;

import java.io.IOException;
import java.util.Scanner;

public class App3 {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el nombre del fichero: ");
        String nombreFichero = sc.nextLine();

        System.out.println("Introduce la temperatura 1: ");
        int temperatura1 = sc.nextInt();
        sc.nextLine();

        System.out.println("Introduce la temperatura 2: ");
        int temperatura2 = sc.nextInt();
        sc.nextLine();

        String rutaApp1 = "src/Ejercicio4/App1.java";

        String ficheroConNombre = "ficherosEjercicios/" + nombreFichero ;

        for (int i = 0 ; i < 4 ; i++) {
            try {

            ProcessBuilder pb = new ProcessBuilder("java", rutaApp1, String.valueOf(temperatura1),
                    String.valueOf(temperatura2), ficheroConNombre);

            Process p = pb.start();

            p.waitFor();

            //Que espere 3 segundos cada vez que corra la app1
            Thread.sleep(3000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        //Llamada a app2
        String rutaApp2 = "src/Ejercicio4/App2.java";

        ProcessBuilder pb2 = new ProcessBuilder("java", rutaApp2, ficheroConNombre);

        pb2.inheritIO();
        Process p2 = pb2.start();

        p2.waitFor();

    }

}
