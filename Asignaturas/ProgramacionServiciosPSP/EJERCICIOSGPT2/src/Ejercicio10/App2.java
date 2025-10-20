package Ejercicio10;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el nombre del archivo: ");
        String nombreArchivo = sc.nextLine();

        String texto = "El mar canta, canta con olas infinitas.\n" +
                "La arena guarda, guarda secretos antiguos.\n" +
                "El viento vuela, vuela sobre montañas lejanas.\n" +
                "Los árboles sueñan, sueñan con cielos azules.\n" +
                "Las aves vuelan, vuelan buscando horizontes.\n" +
                "La gente habla, habla sin escuchar del todo.\n" +
                "El tiempo corre, corre y nunca se detiene.\n" +
                "La vida sigue, sigue siempre adelante.";

        String rutaPaquete = "src/Archivos/";
        File fichero = new File(rutaPaquete+nombreArchivo);

        try {
            FileWriter fr = new FileWriter(fichero);
            BufferedWriter bw = new BufferedWriter(fr);
            bw.write(texto);
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Introduce la palabra que quieras buscar: ");
        String palabraBusqueda = sc.nextLine();

        String rutaApp1 = "src/Ejercicio10/App1.java";

        ProcessBuilder pb  = new ProcessBuilder("java", rutaApp1, rutaPaquete+nombreArchivo, palabraBusqueda);

        pb.inheritIO();
        try {
            Process p = pb.start();
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

}
