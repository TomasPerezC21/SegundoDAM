package Actividad03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Clase1 {

    public static void main(String[] args) {

        Random aleatorio = new Random();

        int[] lista = new int[30];
        for (int i = 0; i < lista.length; i++) {
            lista[i] = aleatorio.nextInt(1, 100)+1;
        }


        System.out.println("Array generado: " +Arrays.toString(lista));

        String[] listaString = new String[lista.length];
        for (int i = 0; i < listaString.length; i++) {
            listaString[i] = String.valueOf(lista[i]);
        }

       StringBuilder listaEspacios = new StringBuilder();

        for (int i = 0; i < listaString.length; i++) {
            listaEspacios.append(listaString[i]).append(" ");
        }

        listaEspacios.deleteCharAt(listaEspacios.length()-1);

        System.out.println("String creado separando por espacios: " +listaEspacios);

        try{

            String rutaFichero = "C:\\Users\\alumno\\Documents\\SegundoDAMTomasPerez\\Asignaturas\\ProgramacionServiciosPSP\\EjercicioNumeros\\src\\main\\java\\Actividad03\\Clase2.java";
            ProcessBuilder pb = new ProcessBuilder("java",rutaFichero, listaEspacios.toString());

            Process p = pb.start();

            BufferedReader entrada = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String linea =  entrada.readLine();
            System.out.println("Salida consola clase 2: "+linea);

            p.waitFor();

        }catch(Exception e){
           e.printStackTrace();
        }


    }


}
