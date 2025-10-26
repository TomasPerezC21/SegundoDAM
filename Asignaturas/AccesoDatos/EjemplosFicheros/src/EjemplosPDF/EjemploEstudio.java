package EjemplosPDF;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EjemploEstudio {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Escribe la ruta: ");
        String ruta = sc.nextLine();

        System.out.println("Nombre: ");
        String nombre = sc.nextLine();

        System.out.println("Cadena 1: ");
        String cadena = sc.nextLine();
        System.out.println("Cadena 2: ");
        String cadena2 = sc.nextLine();
        System.out.println("Cadena 3: ");
        String cadena3 = sc.nextLine();

        try {
            crearfichero(ruta, nombre, cadena, cadena2, cadena3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void crearfichero(String ruta, String nombre, String cadena, String cadena2, String cadena3) throws IOException {
        File fichero = new File(ruta, nombre);
        FileWriter fw = new FileWriter(fichero);
        fw.write(cadena + "\n");
        fw.write(cadena2 + "\n");
        fw.write(cadena3 + "\n");
        fw.close();
    }

}
