package Ejercicio9;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App2 {

    //Le pasa los nombres al ejecutarla con ProcessBuilder.

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce nombre fichero: ");
        String nombre1 = sc.nextLine();

        System.out.println("Contenido a añadir: ");
        String contenido1 = sc.nextLine();

        System.out.println("Introduce nombre fichero 2: ");
        String nombre2 = sc.nextLine();

        System.out.println("Contenido a añadir: ");
        String contenido2 = sc.nextLine();


        File fichero1 = new File("src/Archivos/"+nombre1);
        try {
            FileWriter fw1 = new FileWriter(fichero1);
            fw1.write(contenido1);
            fw1.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File fichero2 = new File("src/Archivos/"+nombre2);
        try{
            FileWriter fw2 = new FileWriter(fichero2);
            fw2.write(contenido2);
            fw2.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }


    }
}
