package Ejercicio2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class App2 {

    public static void main(String[] args) {

        String[] palabras = args[0].split(" ");

        ArrayList<String> palabras2 = new ArrayList<>(Arrays.asList(palabras));
        Collections.sort(palabras2);
        Collections.reverse(palabras2);

        StringBuilder  sb = new StringBuilder();
        for (String palabra : palabras2) {
            sb.append(palabra).append(" ");
        }

        //Borramos último espacio
        sb.deleteCharAt(sb.length()-1);

        System.out.println("Palabras ordenadas a la inversa: " +sb);



    }

}
