package Ejercicio1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class App2 {

    public static void main(String[] args) {

        String nombreFichero = args[0];

        //contador para el numero total de accesos
        int accesos = 0;

        //Arraylist de strings para almacenar las lineas
        ArrayList<String> listaLineas =  new ArrayList<>();

        //ArrayList de usuarios unicos
        ArrayList<String> listaUnicos = new ArrayList<>();


        try {
            FileReader fr = new FileReader(nombreFichero);
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();


            while (linea != null) {
                String[] usuariosTemp = linea.split(" ");
                listaUnicos.add(usuariosTemp[0]);
                listaLineas.add(linea);
                accesos++;
                linea = br.readLine();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String[] tresUltimos = new String[3];

        int i = 0;
        int j = listaLineas.size()-1;

        while (i < tresUltimos.length) {
            tresUltimos[i] = listaLineas.get(j);
            i++;
            j--;
        }

        System.out.println("Número total de accesos: " + accesos);
        System.out.println("3 últimos accesos: ");
        ArrayList<String> listaUltimosAlf = new ArrayList<>();
        for (String u : tresUltimos) {
            listaUltimosAlf.add(u);
        }

        Collections.sort(listaUltimosAlf);

        for (String u : listaUltimosAlf) {
            System.out.println(u);
        }

    }

}
