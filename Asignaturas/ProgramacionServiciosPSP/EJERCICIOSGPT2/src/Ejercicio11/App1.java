package Ejercicio11;

import java.io.*;

public class App1 {

    public static void main(String[] args) {

        String nombreFicheroOriginal = args[0];
        String nombreFicheroNuevo = args[1];

        String ficheroNuevoDestino = "src/Archivos/"+nombreFicheroNuevo;

        File ficheroDefinitivo  = new File(ficheroNuevoDestino);
        try {
            FileWriter fw = new FileWriter(ficheroDefinitivo);
            BufferedWriter bw = new BufferedWriter(fw);
            FileReader fr = new FileReader(nombreFicheroOriginal);
            BufferedReader br = new BufferedReader(fr);
            String linea =  br.readLine();
            while (linea != null) {
                bw.write(linea);
                bw.write("\n");
                linea = br.readLine();
            }
            bw.close();
            fr.close();
            fw.close();
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("====== RUTA DEL FICHERO: "+ ficheroDefinitivo.getAbsolutePath());

        try {
            FileReader fr2 = new FileReader(ficheroDefinitivo);
            BufferedReader br2 = new BufferedReader(fr2);
            String linea = br2.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = br2.readLine();
            }
            fr2.close();
            br2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
