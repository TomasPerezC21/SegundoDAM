package TareaGuillermo2.Ejercicio4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class App1 {

    public static void main(String[] args) {
        Random r = new Random();

        DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaHoraActual = LocalDateTime.now().format(FMT);

        int numero1 = Integer.parseInt(args[0]);
        int numero2 = Integer.parseInt(args[1]);
        String nombreFichero =  args[2];

        int temperatura = r.nextInt(numero1, numero2);

        try {
            generarFichero(fechaHoraActual, temperatura, nombreFichero);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void generarFichero(String fechaHoraActual, int temperatura, String nombreFichero) throws IOException {

        File fichero =  new File(nombreFichero);
        FileWriter fw = new FileWriter(fichero, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(fechaHoraActual+" "+temperatura);
        bw.newLine();
        bw.close();
    }

}
