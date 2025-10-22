package TareaGuillermo2.Ejercicio2;

import java.util.ArrayList;
import java.util.Collections;

public class App2 {

    public static void main(String[] args) {

        String palabrasApp1 = args[0];

        String[] arrayPalabras =  palabrasApp1.split(" ");

        ArrayList<String> palabras = new ArrayList<>();

        for (String palabra : arrayPalabras) {
            palabras.add(palabra);
        }

        Collections.sort(palabras);
        Collections.reverse(palabras);

        StringBuilder devolverPalabras = new StringBuilder();
        for (String palabra : palabras) {
            devolverPalabras.append(palabra).append(" ");
        }

        devolverPalabras.deleteCharAt(devolverPalabras.length()-1);

        System.out.println(devolverPalabras);
    }

}
