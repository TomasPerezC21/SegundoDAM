package Ejercicio1;

import java.io.IOException;
import java.util.Scanner;

public class App2 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String rutaApp1 = ".idea/src/Ejercicio1/App1.java";


        System.out.println("Cómo te llamas?");
        String nombre = sc.nextLine();


        ProcessBuilder pb = new ProcessBuilder();
        pb.command("java", rutaApp1, nombre);

        try {
            pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
