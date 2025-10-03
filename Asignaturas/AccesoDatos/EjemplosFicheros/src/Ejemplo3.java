import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejemplo3 {

    public static void main(String[] args) {
        System.out.println("Pon el directorio: ");
        Scanner sc = new Scanner(System.in);
        String directorio = sc.nextLine();
        crearFichero(directorio);
    }

    public static void crearFichero(String rutaFichero) {

        try {
            FileReader fichero =  new FileReader(rutaFichero);

            int caracter = fichero.read();
            while (caracter != -1) {
                System.out.print((char) caracter);
                caracter = fichero.read();
            }

        } catch (FileNotFoundException e) {
            System.out.println("El fichero " + e.getMessage() + " no existe.");
        } catch (IOException e) {
            System.out.println("Error en la comunicación con el fichero");
        }


    }


}
