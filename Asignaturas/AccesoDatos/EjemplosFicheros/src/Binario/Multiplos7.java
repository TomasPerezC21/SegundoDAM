package Binario;

import java.io.*;

public class Multiplos7 {

    public static void main(String[] args) {


        String directorioFichero = "src/Archivos";
        String nombreFichero = "Multiplos7";

        File archivo = new File(directorioFichero,  nombreFichero);

        try {
            FileOutputStream fis = new FileOutputStream(archivo);
            for (int i = 0; i <= 100 ; i++){
            if (i%7==0){
                fis.write(i);
            }
            }
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            FileInputStream fis = new FileInputStream(archivo);
            int numero = fis.read();
            while (numero!= -1) {
                System.out.print(numero);
                numero = fis.read();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
