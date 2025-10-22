package Ejercicio101Suma;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int numeros;

        ArrayList<Integer> lista = new ArrayList<>();

        while (true) {
            System.out.println("Introduce números (0 para salir): ");
            numeros = sc.nextInt();

            if (numeros==0) {
                break;
            }
            lista.add(numeros);
        }

        //Lista de enteros a Strings
        ArrayList<String> lista2 = new ArrayList();
        for (int i = 0; i < lista.size(); i++) {
            lista2.add(lista.get(i).toString());
        }

        String rutaApp2 = "src/Ejercicio101/App2.java";

        ProcessBuilder pb = new ProcessBuilder("java", rutaApp2);

        try {
            Process p = pb.start();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            for (int i = 0; i < lista2.size(); i++) {
                bw.write(lista2.get(i));
                bw.newLine();
            }
            bw.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea = br.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = br.readLine();
            }
            br.close();

            p.waitFor();

        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }


    }

}
