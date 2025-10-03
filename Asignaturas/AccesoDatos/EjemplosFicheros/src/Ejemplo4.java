import java.io.*;
import java.util.Scanner;

public class Ejemplo4 {


    public static void main(String[] args) {

        System.out.println("Pon el directorio: ");
        Scanner sc = new Scanner(System.in);
        String directorio = sc.nextLine();
        ejemplo4(directorio);


    }

    public static void ejemplo4(String rutaFichero){

        try {
            FileReader fichero = new FileReader(rutaFichero);
            BufferedReader flujo = new BufferedReader(fichero);
            String linea = flujo.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = flujo.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage()+ " no existe");;
        } catch (IOException e) {
            System.out.println("Error en la comunicación en el fichero.");;
        }

    }

}
