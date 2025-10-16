package Ejercicio5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class App2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double suma = 0;

        //Para luego revertir y sacar los dos números más grandes y size para la media
        ArrayList<Integer> lista = new ArrayList<>();

        while(sc.hasNextInt()){
            lista.add(sc.nextInt());
        }

        //Añado esta condición para que mínimo la lista tenga dos números y evitar errores
        if (lista.isEmpty() || lista.size() < 2) {
            System.out.println("No hay números suficientes. Tienen que ser mínimo 2.");
            System.exit(1);
        }

        for (int i = 0; i < lista.size(); i++) {
            suma += lista.get(i);
        }

        //Media
        double media = suma / lista.size();
        //Menor
        int menor = Collections.min(lista);

        //Dos nums top2 de la lista
        int[] top2 = new int[2];

        Collections.sort(lista);
        Collections.reverse(lista);

        for (int i = 0; i < top2.length; i++) {
            top2[i] = lista.get(i);
        }

        //Número más repetido
        int moda = lista.get(0);
        int mejorConteo = 1;

        int numActual = lista.get(0);
        int conteoActual = 1;

        for (int i = 1; i < lista.size(); i++) {
            if (lista.get(i)==numActual) {
                conteoActual++;
            }else{
                if(conteoActual > mejorConteo){
                    moda = numActual;
                    mejorConteo = conteoActual;
                }
            numActual = lista.get(i);
                conteoActual = 1;
            }
        }

        if (conteoActual > mejorConteo){
            moda = numActual;
            mejorConteo = conteoActual;
        }

        //Salida hacia app1 con los resultados
        System.out.println("Dos números mayores: " + Arrays.toString(top2));
        System.out.println("Menor número: " + menor);
        System.out.println("Media: " + media);
        System.out.println("Número más repetido: Número " + moda + " con " + mejorConteo + " apariciones.");




    }
}
