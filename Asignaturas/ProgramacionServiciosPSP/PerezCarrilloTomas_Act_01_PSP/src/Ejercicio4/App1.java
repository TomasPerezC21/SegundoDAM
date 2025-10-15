package Ejercicio4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class App1 {

    public static void main(String[] args) {
        Random r =  new Random();

        LocalDate ahora = LocalDate.now();

        String fecha = ahora.getDayOfMonth() + "/"
                + ahora.getMonthValue() + "/"
                + ahora.getYear();

        String horaActual = String.valueOf(LocalDateTime.now().getHour()) + ':'
                + LocalDateTime.now().getMinute() + ':' + LocalDateTime.now().getSecond();

        int menor;
        int mayor;

        if (Integer.parseInt(args[0]) < Integer.parseInt(args[1])) {
            menor = Integer.parseInt(args[0]);
            mayor = Integer.parseInt(args[1]);
        }else {
            menor = Integer.parseInt(args[1]);
            mayor = Integer.parseInt(args[0]);
        }

        int aleatorio = r.nextInt(menor, mayor + 1);

        String nombreFichero = args[2];

        File fichero = new File(nombreFichero);

        try {
            FileWriter fw = new FileWriter(fichero, true);
            fw.write(fecha + " " + horaActual + " Temperatura: " + aleatorio + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
