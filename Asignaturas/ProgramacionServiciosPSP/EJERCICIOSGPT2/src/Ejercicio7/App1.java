package Ejercicio7;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce nombre del fichero: ");
        String nombre = sc.nextLine();

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

        for(String palabra1 : palabras){
            File fichero = new File("src/Archivos/"+nombre);
            try {
                FileWriter fw = new FileWriter(fichero, false);
                fw.write(palabra1 + System.lineSeparator());
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Contenido del fichero: ");
        try {
            FileReader fr2 = new FileReader("src/Archivos/"+nombre);
            BufferedReader br = new BufferedReader(fr2);
            String linea = br.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
