package Actividad03;

import java.io.*;
import java.util.Random;

public class Clase4 {

    public static void main(String[] args) {

        //StringBuilder aleatoriosNuevos = generarAleatorios();

        String rutaFichero = "C:\\Users\\alumno\\Documents\\SegundoDAMTomasPerez\\Asignaturas\\ProgramacionServiciosPSP\\EjercicioNumeros\\src\\main\\java\\Actividad03\\Clase3.java";

        ProcessBuilder pb = new ProcessBuilder("java", rutaFichero);

        File f = new File("suma.txt");

        pb.redirectOutput(f);

        try {

            Process p = pb.start();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

            for (int i = 1; i < 5; i++) {
                bw.write(String.valueOf(generarAleatorios()));
                bw.write("\n");
            }
            bw.flush();
            bw.close();


            p.waitFor();

            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String linea;

            while ((linea = br.readLine()) != null) {
                System.out.println("Salida de Clase3: " + linea);
            }



        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    public static int generarAleatorios(){

        Random rand = new Random();

        return rand.nextInt(30) + 1;

    }



}
