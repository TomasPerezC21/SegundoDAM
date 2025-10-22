package TareaGuillermo2.Ejercicio4;

import java.io.*;
import java.util.Scanner;

public class App3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String rutaApp1 = "src/TareaGuillermo2/Ejercicio4/App1.java";

        System.out.println("Introduce el nombre del archivo: ");
        String nombre = sc.nextLine();

        System.out.println("Introduce un número: ");
        int numero = sc.nextInt();

        System.out.println("Introduce otro: ");
        int numero2 = sc.nextInt();

        for (int i = 0; i < 4 ; i++){
            ProcessBuilder pb = new ProcessBuilder("java", rutaApp1, String.valueOf(numero), String.valueOf(numero2), nombre);
            try {
                Process p = pb.start();
                p.waitFor();
                Thread.sleep(2000L);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        String rutaApp2 = "src/TareaGuillermo2/Ejercicio4/App2.java";

        ProcessBuilder pb = new ProcessBuilder("java", rutaApp2,nombre);
        try {
            //pb.inheritIO();
            Process p = pb.start();
            InputStreamReader isr = new InputStreamReader(p.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            String linea =  br.readLine();
            while (linea != null){
                System.out.println(linea);
                linea = br.readLine();
            }
            p.waitFor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
