package Ejercicio2;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class App2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> listaUsuarios = new ArrayList<>();

        while(sc.hasNextLine()){
            listaUsuarios.add(sc.nextLine());
        }

        String nombreFichero = "20251023-TOMÁS.md";

        File fichero = new File(nombreFichero);

        for (int i = 0; i < listaUsuarios.size(); i++) {
            try {
                FileWriter fw = new FileWriter(fichero);
                BufferedWriter bw = new BufferedWriter(fw);
                String[]datos =  listaUsuarios.get(i).split(" ");
                String metodo = datos[2];
                String escribir = validarContra(metodo);
                bw.write(datos[0] + escribir);
                bw.newLine();
                bw.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

//            String rutaApp1 = "src/Ejercicio2/App1.java";
//            ProcessBuilder pb2 = new ProcessBuilder("java", rutaApp1, nombreFichero);
//            try {
//                Process p2 = pb2.start();
//                p2.waitFor();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }

            try {
                FileReader fr = new FileReader(nombreFichero);
                BufferedReader br = new BufferedReader(fr);
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static String validarContra(String contra){
        String contraSalida = "";
        if (contra.length() < 8){
            contraSalida = "La contraseña no llega a 8 caracteres";
        }

       if (!contra.matches("^[A-Za-z0-9]*$")){
           contraSalida = "La contraseña no tiene letras y números";
       }

        return contraSalida;
    }

//    public static String validarFechaNac(String fechaNac){
//        String fechaNacSalida = "";
//        DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//
//
//
//    }
}
