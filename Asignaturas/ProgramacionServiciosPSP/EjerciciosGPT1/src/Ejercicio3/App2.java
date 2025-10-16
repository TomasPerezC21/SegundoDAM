package Ejercicio3;

import java.io.IOException;
import java.util.Scanner;

public class App2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Escribe una frase:");

        String frase = sc.nextLine();

        String ruta = "src/Ejercicio3/App1.java";

        ProcessBuilder pb = new ProcessBuilder("java", ruta,  frase);

        pb.inheritIO();

        try {
            Process p = pb.start();
            p.waitFor();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
