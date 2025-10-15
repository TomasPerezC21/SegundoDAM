package Ejercicio1;

import java.io.IOException;
import java.util.Scanner;

public class MiniCalcMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el valor 1: ");
        int valor1 = sc.nextInt();
        String valor1String = String.valueOf(valor1);

        System.out.println("Introduce el valor 2: ");
        int valor2 = sc.nextInt();
        String valor2String = String.valueOf(valor2);

        System.out.println("Introduce el operador (+,-,*,/): ");
        char operador = sc.next().charAt(0);
        String operadorString = String.valueOf(operador);

        String claseEje = "src/Ejercicio1/MiniCalc.java";

        ProcessBuilder pb = new ProcessBuilder("java", claseEje, valor1String, valor2String, operadorString);

        try {
            pb.inheritIO();
            Process p = pb.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }




}
