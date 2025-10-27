package FichaJuana;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class A04Bien {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String rutaFichero = "src/Archivos/TextoJuana";

        System.out.println("Introduce una palabra: ");
        String palabra = sc.nextLine().toLowerCase();

        int fila;
        try {
            fila = devolverFila(rutaFichero, palabra);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (fila != -1 ){
            System.out.println("La palabra aparece en la línea: " + fila);
        }else{
            System.out.println("La palabra no aparece en ninguna linea");
        }

    }

    public static int devolverFila(String rutaFichero, String palabra) throws IOException {
        int fila = 0;

        FileReader fr = new FileReader(rutaFichero);
        BufferedReader br = new BufferedReader(fr);
        String linea = br.readLine();
        boolean encontrado = false;

        while (linea != null) {
            fila++;
            if(linea.contains(palabra)){
                encontrado = true;
                break;
            }
            linea = br.readLine().toLowerCase();
        }

        if(encontrado){
            return fila;
        }else{
            return -1;
        }

    }

}
