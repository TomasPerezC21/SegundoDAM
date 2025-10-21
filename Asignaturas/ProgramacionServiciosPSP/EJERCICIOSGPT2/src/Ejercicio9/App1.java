package Ejercicio9;

import java.io.File;

public class App1 {

    //Recibe dos nombres de archivo por argumento y crea un tercero con el contenido combinado.

    public static void main(String[] args) {

        String nombre1 = args[0];
        String nombre2 = args[1];

        String rutaPaquete = "src/Archivos/";
        String nombre3 = "FicheroCombinado";

        File fichero = new File(rutaPaquete+nombre3);




    }

}
