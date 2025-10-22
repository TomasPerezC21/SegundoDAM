package Ejercicio101Suma;

import java.util.ArrayList;
import java.util.Scanner;

public class App2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int suma = 0;

        ArrayList<Integer> lista = new ArrayList();

        while(sc.hasNextInt()){
            lista.add(sc.nextInt());
        }

        for(int i=0;i<lista.size();i++){
            System.out.println(lista.get(i));
            suma += lista.get(i);
        }

        System.out.println("Suma: "+suma);

    }

}
