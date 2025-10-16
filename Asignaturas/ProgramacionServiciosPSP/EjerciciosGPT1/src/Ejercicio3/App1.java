package Ejercicio3;

import java.util.Arrays;

public class App1 {

    public static void main(String[] args) {


        String frase = args[0];
        char[] fraseAlreves = new char[frase.length()];


        int i = frase.length() - 1;
        int j = 0;

        while (i >= 0) {
            fraseAlreves[j] = frase.charAt(i);
            i--;
            j++;
        }

        String fraseChar =  new String(fraseAlreves);

        System.out.println("Frase al reves: " + fraseChar);
    }

}
