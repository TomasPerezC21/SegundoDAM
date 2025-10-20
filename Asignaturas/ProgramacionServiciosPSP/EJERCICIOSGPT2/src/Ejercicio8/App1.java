package Ejercicio8;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String nombreFichero = args[0];

        String palabra;

        ArrayList<String> palabras = new ArrayList<>();

        while(true){
            System.out.println("Introduce una palabra (fin para salir): ");
            palabra = sc.nextLine();

            if (palabra.equalsIgnoreCase("fin")){
                break;
            }
            palabras.add(palabra);
        }


            File fichero = new File("src/Archivos/"+nombreFichero);
            try {
                FileWriter fw = new FileWriter(fichero, false);
                for (String pal: palabras) {
                    fw.write(pal + System.lineSeparator());
                }
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        System.out.println("Contenido del fichero: ");
        int lineas = 0;
        try {
            FileReader fr2 = new FileReader("src/Archivos/"+nombreFichero);
            BufferedReader br = new BufferedReader(fr2);
            String linea = br.readLine();
            while (linea != null) {
                System.out.println(linea);
                lineas++;
                linea = br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Lineas: " + lineas);






    }

}
