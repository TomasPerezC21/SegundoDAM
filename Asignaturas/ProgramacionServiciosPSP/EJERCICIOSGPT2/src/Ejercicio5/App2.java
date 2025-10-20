package Ejercicio5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String rutaApp1 = "src/Ejercicio5/App1.java";


        for (int i = 0 ; i < 3 ; i++){
            System.out.println("Introduce una palabra: ");
            String palabra = sc.nextLine();
            ProcessBuilder pb = new ProcessBuilder("java", rutaApp1,  palabra);
            //pb.inheritIO();
            try {
                Process p = pb.start();
                p.waitFor();
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }

        }

        try {
            FileReader fr = new FileReader("ejercicio5.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            while(linea!=null){
                System.out.println(linea);
                linea = br.readLine();
            }
            br.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }





    }

}
