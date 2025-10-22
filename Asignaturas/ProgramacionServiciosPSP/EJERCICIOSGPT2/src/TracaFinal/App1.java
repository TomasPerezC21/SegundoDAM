package TracaFinal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class App1 {

    public static void main(String[] args) {

        String nombresAlumnos = args[0];
        String notasAlumnos = args[1];
        String nombreFichero = args[2];

        String[] alumnosSplit = nombresAlumnos.split(" ");
        ArrayList<String> nombresAlumnosArrayList = new ArrayList<>();
        for (String alumno : alumnosSplit) {
            nombresAlumnosArrayList.add(alumno);
        }

        String[] notasSplit = notasAlumnos.split(" ");
        ArrayList<String> notasAlumnosArrayList = new ArrayList<>();
        for (String notas : notasSplit) {
            notasAlumnosArrayList.add(notas);
        }

        File fichero = new File(nombreFichero);
        try {
            FileWriter fw = new FileWriter(fichero);
            BufferedWriter bw = new BufferedWriter(fw);

           
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
