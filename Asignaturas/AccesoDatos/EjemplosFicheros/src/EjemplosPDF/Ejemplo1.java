package EjemplosPDF;

import java.io.File;
import java.util.Scanner;

public class Ejemplo1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la ruta absoluta del directorio: ");

        String ruta = sc.nextLine();

        ejemplo1(ruta);


    }

    public static void ejemplo1(String rutaFichero){

        File fichero = new File(rutaFichero);

        String[] listaFicheros = fichero.list();

        System.out.println("Lista de ficheros: ");
        for (String ficheros : listaFicheros){
            System.out.println(ficheros);
        }

    }


}
