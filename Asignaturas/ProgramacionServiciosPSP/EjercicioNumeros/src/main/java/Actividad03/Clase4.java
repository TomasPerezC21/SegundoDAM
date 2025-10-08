package Actividad03;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Random;

public class Clase4 {

    public static void main(String[] args) {

        //StringBuilder aleatoriosNuevos = generarAleatorios();

        String rutaFichero = "C:\\Users\\alumno\\Documents\\SegundoDAMTomasPerez\\Asignaturas\\ProgramacionServiciosPSP\\EjercicioNumeros\\src\\main\\java\\Actividad03\\Clase3.java";

        ProcessBuilder pb = new ProcessBuilder("java", rutaFichero);

        try {

            Process p = pb.start();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

            for (int i = 1; i <= 5; i++) {
                bw.write(generarAleatorios());
            }


        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    public static int generarAleatorios(){

        Random rand = new Random();



        for (int i = 0; i < 5; i++) return rand.nextInt(1, 30) + 1;

        return 0;
    }



}
