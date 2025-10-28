package Binario;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Formattable;
import java.util.HashMap;
import java.util.Map;

public class Otro {

    private static final String NOMBRE_FICHERO = "src/Archivos/NuevoJuana";
    private static final String NOMBRE_FICHERO_2 = "src/Archivos/TextoJuana";

    public static void main(String[] args) {

        ArrayList<String> palabrasClave = new ArrayList<>();
        palabrasClave.add("Crear");
        palabrasClave.add("Programa");
        palabrasClave.add("Ignorando");
        palabrasClave.add("Fichero");
        palabrasClave.add("ArrayList");
        palabrasClave.add("clave");


        try {
            crearFichero(NOMBRE_FICHERO, NOMBRE_FICHERO_2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HashMap<String, Integer> imprimir;

        try {
             imprimir = revisarFichero(NOMBRE_FICHERO, palabrasClave);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Map.Entry<String, Integer> entrada : imprimir.entrySet()) {
            System.out.println(entrada.getKey() + " - " + entrada.getValue());
        }


    }

    public static void crearFichero(String ficheroNuevo, String ficheroAntiguo) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(ficheroAntiguo));
        BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroNuevo));
        String linea = br.readLine();
        while (linea != null) {
            bw.write(linea);
            bw.newLine();
            linea = br.readLine();
        }
        bw.close();
        br.close();

    }

    public static HashMap revisarFichero(String ficheroNuevo, ArrayList<String> palabrasClave) throws IOException {

//        String[] palabras = new String[palabrasClave.size()];
//        for (int i = 0; i < palabrasClave.size(); i++) {
//            palabras[i] = palabrasClave.get(i).toLowerCase();
//        }
        BufferedReader br = new BufferedReader(new FileReader(ficheroNuevo));

        HashMap<String, Integer> resultado = new HashMap<>();

        ArrayList<String> palabrasMinus = new ArrayList<>();
        for (String p : palabrasClave) { palabrasMinus.add(p.toLowerCase()); }

        int contadorFilas = 0;

        String linea = br.readLine();
        while (linea != null) {
            String lineaLower = linea.toLowerCase();

            contadorFilas = contadorFilas + 1;

            for (int i = 0; i < palabrasMinus.size(); i++) {
                if (!resultado.containsKey(palabrasClave.get(i)) && lineaLower.contains(palabrasMinus.get(i))) {
                    resultado.put(palabrasClave.get(i), contadorFilas);
                }
            }
        linea = br.readLine();
        }
        return resultado;
    }

}
