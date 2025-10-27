package EjerciciosGPT;

import java.io.*;
import java.util.ArrayList;
import java.util.Formattable;

public class A13 {

    public static void main(String[] args) {

        String texto1 = "src/Archivos/A13";
        String texto2 = "src/Archivos/A13,2";

        try {
            crearRemix(texto1,texto2);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void crearRemix(String texto1,String texto2) throws IOException {
        File ArchivoFinal = new File("src/Archivos/ResultadoA13");

        FileReader fr1 = new FileReader(texto1);
        FileReader fr2 = new FileReader(texto2);
        BufferedReader br1 = new BufferedReader(fr1);
        BufferedReader br2 = new BufferedReader(fr2);

        //Contar lineas para ver que texto tiene mas
        String linea1 = br1.readLine();
        String linea2 = br2.readLine();
        ArrayList<String> contadorLineas1 = new ArrayList();
        ArrayList<String> contadorLineas2 = new ArrayList();

        while (linea1 != null) {
            contadorLineas1.add(linea1);
            linea1 = br1.readLine();
        }
        while (linea2 != null) {
            contadorLineas2.add(linea2);
            linea2 = br2.readLine();
        }

        if (contadorLineas2.size() > contadorLineas1.size()) {
            ArrayList<String> temp = contadorLineas1;
            contadorLineas1 = contadorLineas2;
            contadorLineas2 = temp;
        }

        FileWriter fw1 = new FileWriter(ArchivoFinal);
        BufferedWriter bw1 = new BufferedWriter(fw1);

        for (int i = 0; i < contadorLineas2.size(); i++) {
            bw1.write(contadorLineas1.get(i)+"\n");
            bw1.write(contadorLineas2.get(i)+"\n");
        }
        for (int i = contadorLineas2.size(); i < contadorLineas1.size(); i++) {
            bw1.write(contadorLineas1.get(i)+"\n");
        }
        bw1.close();

    }

}
