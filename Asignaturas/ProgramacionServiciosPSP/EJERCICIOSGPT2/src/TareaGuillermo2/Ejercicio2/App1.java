package TareaGuillermo2.Ejercicio2;

import java.util.Scanner;

public class App1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Cuántas palabras quieres meter: ");
        int n =  sc.nextInt();
        sc.nextLine();

        StringBuilder palabras = new StringBuilder();

        for (int i = 0; i < n; i++) {
            System.out.println("Introduce palabra: ");
            palabras.append(sc.nextLine()).append(" ");
        }

        palabras.deleteCharAt(palabras.length()-1);

        String rutaApp2 = "src/TareaGuillermo/Ejercicio2/App2.java";

        ProcessBuilder pb = new ProcessBuilder("java", rutaApp2, palabras.toString());

        try {
            pb.inheritIO();
            Process p = pb.start();
            p.waitFor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
