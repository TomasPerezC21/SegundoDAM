package Ejercicio3;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class App1 {

    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Argumentos incorrectos. Son 2.");
            System.exit(1);
        }

        String nombre = args[0];
        String opcion = args[1];


        String fecha = LocalDate.now().getDayOfMonth() + "/"
                + LocalDate.now().getMonthValue() + "/"
                + LocalDate.now().getYear();

        String horaActual = String.valueOf(LocalDateTime.now().getHour()) + ':'
                + LocalDateTime.now().getMinute() + ':' + LocalDateTime.now().getSecond();

        generarFichero(nombre, opcion, fecha, horaActual);

    }

    public static void generarFichero(String nombre, String op, String fechaActual, String horaActual) {

        switch (op) {
            case "1":
                try {
                    File archivo = new File(nombre);
                    FileWriter fw = new FileWriter(archivo, false);

                    fw.write(fechaActual + " a las " + horaActual + System.lineSeparator());
                    fw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;

            case "2":
                try {
                    File archivo = new File( nombre);
                    FileWriter fw = new FileWriter(archivo, true);

                    fw.write(fechaActual + " a las " + horaActual + System.lineSeparator());
                    fw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
        }

    }





}
