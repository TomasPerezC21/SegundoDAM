package Ejercicio1;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class App3 {

    public static ArrayList<String> listaUsuarios = new ArrayList<>();

    public static void rellenarListaUsuarios(String[] ArrayUsuarios) {
        listaUsuarios.addAll(Arrays.asList(ArrayUsuarios));
    }

    public static void main(String[] args) {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce nombre del fichero: ");
        String nombreFichero = sc.nextLine();

        String[] ArrayUsuarios = new String[4];
        ArrayUsuarios[0] = "TomásPérez";
        ArrayUsuarios[1] = "GuillermoPalazón";
        ArrayUsuarios[2] = "ÁlvaroGuy";
        ArrayUsuarios[3] = "BorjaGarcía";

        rellenarListaUsuarios(ArrayUsuarios);
        ArrayList<String> usuariosTemp = listaUsuarios;
        int numLlamadas = r.nextInt(3, 6);


        int usuarioAleatorio;
        int usuarioAleatorioTemportal;

        String rutaApp1 = "src/Ejercicio1/App1.java";


        for (int i = 0; i < numLlamadas; i++) {

                usuarioAleatorio = r.nextInt(listaUsuarios.size());

                String usuario = listaUsuarios.get(usuarioAleatorio);

                ProcessBuilder pb = new ProcessBuilder("java", rutaApp1, nombreFichero, usuario);
                try {
                    Process p = pb.start();
                    p.waitFor();
                    Thread.sleep(1500);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        }

        String rutaApp2 = "src/Ejercicio1/App2.java";

        ProcessBuilder pb2 = new ProcessBuilder("java", rutaApp2, nombreFichero);
        try {
            pb2.inheritIO();
            Process p = pb2.start();
            p.waitFor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
