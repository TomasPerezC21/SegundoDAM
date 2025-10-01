package Actividad02;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Clase1 {

    public static void main(String[] args) {
        Random rand = new Random();

        if (args.length > 0) {
            int[] numeros = new int[Integer.parseInt(args[0])];

            int valor1 = Integer.parseInt(args[1]);
            int valor2 = Integer.parseInt(args[2]);

            for (int i = 0; i < numeros.length; i++) {
                numeros[i] = rand.nextInt(valor1, valor2) + 1;
            }

            Arrays.sort(numeros);
            System.out.println(Arrays.toString(numeros));
        }

    }
}
