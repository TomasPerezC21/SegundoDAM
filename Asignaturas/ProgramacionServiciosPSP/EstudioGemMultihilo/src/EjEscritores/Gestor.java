package EjEscritores;
import java.io.*;

public class Gestor {

    private int lectores = 0;
    private boolean escribiendo = false;
    private int escritoresCola = 0; // Prioridad para escritores

    public synchronized void escribir(String texto) throws Exception {
        escritoresCola++; // Pido turno
        while (lectores > 0 || escribiendo) {
            wait();
        }
        escritoresCola--; // Ya entro
        escribiendo = true;

        // --- ESCRIBIR EN FICHERO (Append = true) ---
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("datos.txt", true))) {
            bw.write(texto);
            bw.newLine();
            System.out.println("Escrito: " + texto);
        }

        escribiendo = false;
        notifyAll();
    }

    public synchronized void leer() throws Exception {
        // Si hay alguien escribiendo O un escritor esperando, yo espero
        while (escribiendo || escritoresCola > 0) {
            wait();
        }
        lectores++;

        // --- LEER FICHERO ---
        try (BufferedReader br = new BufferedReader(new FileReader("datos.txt"))) {
            String linea;
            System.out.println("--- Leyendo ---");
            while ((linea = br.readLine()) != null) {
                System.out.printf("%s\n", linea);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichero vacío.");
        }

        lectores--;
        notifyAll();
    }
}