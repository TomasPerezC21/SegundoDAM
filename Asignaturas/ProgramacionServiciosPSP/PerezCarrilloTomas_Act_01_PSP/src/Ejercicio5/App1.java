package Ejercicio5;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class App1 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> lista = new ArrayList<>();
        System.out.println("Introduce un numero: ");
        int numero = sc.nextInt();

        while(numero > 0){
            System.out.println( "(Introduce 0 o negativo si quieres parar)."
                    + System.lineSeparator()+  "Introduce un numero: ");
            lista.add(numero);
            numero = sc.nextInt();

        }

        //Paso la lista a una lista de Strings
        String[] listaString = new String[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            listaString[i] = lista.get(i).toString();
        }

        String rutaApp2 = "src/Ejercicio5/App2.java";

        ProcessBuilder pb = new ProcessBuilder("java", rutaApp2);

        try {
           Process p = pb.start();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            for (int i = 0; i < listaString.length; i++) {
                bw.write(listaString[i]);
                bw.newLine();
            }
            bw.flush();
            bw.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea = br.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = br.readLine();
            }
            br.close();

            p.waitFor();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }





    }

}
