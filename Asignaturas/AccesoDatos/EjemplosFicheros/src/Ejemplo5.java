import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Ejemplo5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Pon el directorio: ");
        String directorio = sc.nextLine();
        System.out.println("Nombre del archivo: ");
        String nombre = sc.nextLine();
        System.out.println("Pon cadena 1");
        String cadena1 = sc.nextLine();
        System.out.println("Pon cadena 2");
        String cadena2 = sc.nextLine();
        System.out.println("Pon cadena 3");
        String cadena3 = sc.nextLine();

        ejemplo5(directorio, nombre, cadena1, cadena2, cadena3);

    }

    public static void ejemplo5(String rutaDirectorio,String nombre, String cadena1,  String cadena2, String cadena3) {

        try{
            File archivo = new File(rutaDirectorio,  nombre);
            FileWriter fichero = new FileWriter(archivo, true);
            fichero.write(cadena1);
            fichero.write(cadena2);
            fichero.write(cadena3);
            fichero.close();
        }catch(Exception e){
            System.out.println("Error en la ruta del fichero");
        }

    }

}
