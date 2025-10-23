package Ejercicio1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class App1 {

    public static void main(String[] args) {

        String nombreFichero = args[0];
        String nombreUsuario = args[1];

        LocalDateTime horaActual = LocalDateTime.now();
        LocalDate fechaActual = LocalDate.now();

        StringBuilder lineaFichero = new StringBuilder();

        lineaFichero.append(nombreUsuario).append(" accedió al sistema el día ")
                .append(fechaActual.getDayOfMonth()).append(" del ").append(fechaActual.getMonthValue())
                .append(" de ").append(fechaActual.getYear()).append(" a las ").append(horaActual.getHour())
                .append(":").append(horaActual.getMinute()).append(":").append(horaActual.getSecond()).append(" horas.");

        File fichero =  new File(nombreFichero);
        try {
            if(fichero.exists()) {
                FileWriter fw = new FileWriter(fichero, true);
                fw.write(lineaFichero.toString());
                fw.write("\n");
                fw.close();

            }
            else {
                FileWriter fw = new FileWriter(fichero, false);
                fw.write(lineaFichero.toString());
                fw.write(System.lineSeparator());
                fw.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }




    }

}
