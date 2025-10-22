package Ejercicio13Mayusculas;

import java.util.ArrayList;
import java.util.Scanner;

public class App2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<String> lista = new ArrayList<String>();

        while(sc.hasNextLine()){
            lista.add(sc.nextLine().toUpperCase());
        }

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
    }

}
