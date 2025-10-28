package Ejercicio1;

import java.io.*;
import java.util.Scanner;

public class Ejercicio1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String rutaFicheroLeer = "src/Archivos/Ejercicio1";

        System.out.println("Escribe nombre del fichero nuevo: ");
        String nombreFicheroNuevo = sc.nextLine();

        String rutaFicheroNuevo = "src/Archivos/" + nombreFicheroNuevo;

        try {
            crearFichero(rutaFicheroLeer, rutaFicheroNuevo);
        } catch (IOException e) {
            System.err.println(e.getMessage());;
        }
    }

    public static void crearFichero(String rutaFichero, String rutaFicheroNuevo) throws IOException {

        File fichero = new File(rutaFichero);
        if (!fichero.exists()) {
            System.err.println("El fichero no existe.");
        }

        BufferedReader br = new BufferedReader(new FileReader(rutaFichero));
        BufferedWriter bw = new BufferedWriter(new FileWriter(rutaFicheroNuevo));

        String linea = br.readLine();
        while (linea != null) {
            String lineaMayus = linea.toUpperCase();
            bw.write(lineaMayus);
            bw.newLine();
            linea = br.readLine();
        }
        bw.close();
        br.close();

    }


}
