package Actividad03;

import java.util.Scanner;

public class Clase3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int resultado = 0;
        while(sc.hasNextInt()){
            resultado+=sc.nextInt();
        }

        System.out.println(resultado);


    }


}
