package Ejercicio2;

import java.io.IOException;
import java.util.Scanner;

public class App1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("¿Cuántas palabras quieres introducir?");
        int n = sc.nextInt();
        sc.nextLine();

        StringBuilder palabrasEspacio = new StringBuilder();

        for (int i = 0; i < n; i++) {
            System.out.println("Palabra " + (i + 1) + ":");
            palabrasEspacio.append(sc.nextLine()).append(" ");
        }

        //Borrar último espacio
        palabrasEspacio.deleteCharAt(palabrasEspacio.length() - 1);

        String rutaApp2 ="src/Ejercicio2/App2.java";

        ProcessBuilder pb = new ProcessBuilder("java", rutaApp2, palabrasEspacio.toString());

        try {
            pb.inheritIO();
            Process p = pb.start();
            //p.waitFor();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
