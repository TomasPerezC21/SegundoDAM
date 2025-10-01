package Actividad02;

import java.io.File;
import java.util.Scanner;

public class Clase2 {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        System.out.println("Cuántos números ?");
        int numeros = sc.nextInt();
        String num = String.valueOf(numeros);

        System.out.println("Mínimo");
        int minimo = sc.nextInt();
        String min = String.valueOf(minimo);

        System.out.println("Máximo");
        int maximo = sc.nextInt();
        String max = String.valueOf(maximo);

        ProcessBuilder pb = new ProcessBuilder("java","Actividad02.Clase1", num, min, max);

        File f = new File("target/classes/");
        pb.directory(f);

        try{
            pb.inheritIO();
            pb.start();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }

    }
}
