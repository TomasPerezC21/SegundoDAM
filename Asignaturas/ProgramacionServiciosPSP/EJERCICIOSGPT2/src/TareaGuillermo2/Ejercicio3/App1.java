package TareaGuillermo2.Ejercicio3;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class App1 {

    public static void main(String[] args) {

        LocalDate ahoraFecha = LocalDate.now();
        LocalDateTime ahoraHora = LocalDateTime.now();

        StringBuilder fechaActual = new StringBuilder();
        fechaActual.append(ahoraFecha.getDayOfMonth()).append("/").append(ahoraFecha.getMonthValue()).append("/").append(ahoraFecha.getYear());

        StringBuilder horaActual = new StringBuilder();
        horaActual.append(ahoraHora.getHour()).append(":").append(ahoraHora.getMinute()).append(":").append(ahoraHora.getSecond());

        String fechaFile = fechaActual.toString();
        String horaFile = horaActual.toString();

        String nombreFichero = args[0];
        String modo = args[1];

        try {
            generarFichero(nombreFichero, modo, fechaFile, horaFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void generarFichero(String nombreFichero, String modo, String fecha, String hora) throws IOException {
        File archivo = new File(nombreFichero);

        switch (modo) {
            case "1":
                //System.out.println("El fichero se sobreescribirá.");
                try {
                    FileWriter fw = new FileWriter(archivo, false);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(fecha+" a las " + hora);
                    bw.newLine();
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;

                case "2":
                    //System.out.println("El fichero mantendrá las líneas anteriores.");
                    try {
                        FileWriter fw = new FileWriter(archivo, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(fecha+" a las " + hora);
                        bw.newLine();
                        bw.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
        }


    }



}
