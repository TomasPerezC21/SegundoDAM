import java.io.File;
import java.io.IOException;

public class Prueba {

    public static void main(String[] args) {

        String rutaHijo = "C:\\Users\\alumno\\Documents\\SegundoDAMTomasPerez\\Asignaturas\\ProgramacionServiciosPSP\\EjercicioNumeros\\src\\main\\java\\PruebaHijo.java";

        ProcessBuilder pb = new ProcessBuilder("java", rutaHijo);

        File f = new File("prueba.txt");


        pb.redirectOutput(f);

        try {
            Process p = pb.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
