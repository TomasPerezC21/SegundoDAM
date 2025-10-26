package FichaJuana;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class A02 {


    public static void main(String[] args) {
        Random r = new  Random();
        String fichero = "src/Archivos/A02";

        try {
            rellenarFichero(r, fichero);
            comprobarFichero(fichero);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static void rellenarFichero(Random r, String fichero) throws IOException {

        int cantidadNumeros = r.nextInt(25);
        File archivo = new File(fichero);
        FileWriter fw = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < cantidadNumeros; i++) {
            bw.write(r.nextInt(1, 50) + " ");
        }
        bw.close();
        fw.close();
    }

    public static void comprobarFichero(String fichero) throws IOException {

        FileReader fr = new FileReader(fichero);
        BufferedReader br = new BufferedReader(fr);
        String linea = br.readLine();
        String[] numeros = linea.split(" ");
        ArrayList<Integer> lista = new ArrayList<>();
        int suma = 0;
        for (int i = 0; i < numeros.length; i++) {
            lista.add(Integer.parseInt(numeros[i]));
            suma += lista.get(i);
        }

        Collections.sort(lista);
        System.out.println("Número más alto del fichero: " + lista.getLast());
        System.out.println("Número menor: " + lista.getFirst());
        System.out.println("Total de números: " + lista.size());
        System.out.println("Media: " + (suma/lista.size()));
        System.out.println("Suma de mi mes y dia de nacimiento = 34. Mayores de 34: ");
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i) > 34) {
                System.out.print(lista.get(i) + " ");
            }
        }


    }
}
