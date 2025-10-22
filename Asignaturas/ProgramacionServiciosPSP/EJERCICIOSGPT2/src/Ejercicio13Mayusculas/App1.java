package Ejercicio13Mayusculas;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<String> lista = new ArrayList<>();

        String frase="";

        while(true){
            System.out.println("Introduce frases (fin para parar): ");
            frase = sc.nextLine();
            if(frase.equalsIgnoreCase("fin")){
                break;
            }
            lista.add(frase);
        }

        String rutaApp2 = "src/Ejercicio13/App2.java";

        ProcessBuilder pb = new ProcessBuilder("java", rutaApp2);

        try {
            Process p = pb.start();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            for (int i=0; i<lista.size();i++){
                bw.write(lista.get(i));
                bw.newLine();
            }
            bw.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea = br.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = br.readLine();
            }

            p.waitFor();

        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }


    }

}
