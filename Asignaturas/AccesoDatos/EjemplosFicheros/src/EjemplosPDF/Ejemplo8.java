package EjemplosPDF;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejemplo8 {

    public static void main(String[] args) {

        ejemplo8();
        System.out.println("Fichero binario generado correctamente.");
        ejemplo9();
    }

    public static void ejemplo8(){
        try {
            FileOutputStream fos = new FileOutputStream("prueba.dat");

            for (int i = 0; i < 100; i++) {
                if (i % 7 == 0){
                    fos.write(i);
                }
            }
            fos.close();


        } catch (FileNotFoundException e) {
            System.out.println("Error de E/S a fichero binario: "  + e.getMessage());;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void ejemplo9(){
        try {
            FileInputStream fis = new FileInputStream("prueba.dat");

            //int entero = fis.read();

            while(fis.available()>0){
                System.out.println(fis.read());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error de E/S a fichero binario: "  + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
