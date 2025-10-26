package FichaJuana;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class A01 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String rutaFichero = "src/Archivos/A01";

        ArrayList<String> cadenas = new ArrayList<>();
        cadenas.add("Hola soy Tomás");
        cadenas.add("Me gusta la play");
        cadenas.add("Y los bocatas de chorizo");
        cadenas.add("Zarrapastrosas fuera");

        System.out.println("Introduce A o D: ");
        String opcion = String.valueOf(sc.next().charAt(0)).toUpperCase();

        while (!opcion.equalsIgnoreCase("A") && !opcion.equalsIgnoreCase("D")) {
            System.out.println("Vuelva a intentarlo (A o D): ");
            opcion = String.valueOf(sc.next().charAt(0)).toUpperCase();
        }

        try {
            metodo(rutaFichero, cadenas, opcion);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void metodo(String rutaFichero, ArrayList<String> cadenasCaracteres, String tipoOrdenacion) throws IOException {
        int filas = 0;
        File fichero = new File(rutaFichero);
        FileWriter fw = new FileWriter(fichero, false);
        BufferedWriter bw = new BufferedWriter(fw);

        // ordenar asc o desc
        Collections.sort(cadenasCaracteres);
        if (tipoOrdenacion.equals("D")) {
            Collections.reverse(cadenasCaracteres);
        }

        // escribir líneas
        for (String cadena : cadenasCaracteres) {
            bw.write(cadena);
            bw.newLine();
            filas++;
        }

        // escribir contador
        bw.write("Número de líneas guardadas " + filas);
        bw.close();
        }


    }


