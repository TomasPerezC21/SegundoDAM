package Ejercicio4;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class App1 {

    public static void main(String[] args) {

        LocalDate ahora = LocalDate.now();

        String fechaActual = ahora.toString();

        String horaActual = LocalDateTime.now().toString();

        System.out.println("Fecha: " + fechaActual + " Hora: " + horaActual);



    }

}
