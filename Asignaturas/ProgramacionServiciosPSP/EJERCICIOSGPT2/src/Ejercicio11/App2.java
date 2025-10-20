package Ejercicio11;

import java.io.IOException;
import java.util.Scanner;

public class App2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el nombre del archivo: ");
        String nombre = sc.nextLine();

        System.out.println("Introduce el nombre nuevo pa cambiarlo: ");
        String nuevo =  sc.nextLine();

        String rutaApp1 = "src/Ejercicio11/App1.java";

        String archivoConRuta = "src/Archivos/"+nombre;

        ProcessBuilder pb = new ProcessBuilder("java", rutaApp1, archivoConRuta, nuevo);

        pb.inheritIO();

        try {
            Process p = pb.start();
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

}
