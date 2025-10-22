package TareaGuillermo2.Ejercicio1;

import java.io.IOException;
import java.util.Scanner;

public class App2 {

    public static void main(String[] args) {

        System.out.println("Introduce el num 1: ");

        Scanner sc = new Scanner(System.in);
        double num1 = sc.nextDouble();
        sc.nextLine();

        System.out.println("Introduce el num 2: ");
        double num2 = sc.nextDouble();
        sc.nextLine();


        System.out.println("Introduce el operador (+,-,*,/): ");
        String operador = sc.nextLine();
        sc.close();

        String rutaApp1 = "src/TareaGuillermo2/Ejercicio1/App1.java";

        ProcessBuilder pb = new ProcessBuilder("java", rutaApp1, String.valueOf(num1), String.valueOf(num2), operador);

        try {
            pb.inheritIO();
            Process p = pb.start();
            p.waitFor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
