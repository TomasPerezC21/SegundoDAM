package Ejercicio8;


import java.io.IOException;
import java.util.Scanner;

public class App2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce nombre fichero: ");
        String nombreFichero = sc.nextLine();

        String rutaApp1 = "src/Ejercicio8/App1.java";

        ProcessBuilder pb  = new ProcessBuilder("java", rutaApp1, nombreFichero);

        pb.inheritIO();
        try {
            Process p = pb.start();
            p.waitFor();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }



    }

}
