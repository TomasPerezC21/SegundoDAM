package Actividad03;

import java.util.Arrays;

public class Clase2 {

    public static void main(String[] args) {

        if  (args.length == 0){
            System.out.println("No se recibieron argumentos");
        }

        else{

            String listaRecibida = args[0];

            String[] listaStrings = listaRecibida.split(" ");

            int[] listaNumeros = new int[listaStrings.length];

            for (int i = 0; i < listaStrings.length; i++) {
                listaNumeros[i] = Integer.parseInt(listaStrings[i]);
            }

            Arrays.sort(listaNumeros);
            System.out.println("Lista ordenada " + Arrays.toString(listaNumeros));

        }






    }


}
