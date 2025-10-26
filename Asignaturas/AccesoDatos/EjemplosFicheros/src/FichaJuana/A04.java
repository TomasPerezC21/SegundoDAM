package FichaJuana;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.Scanner;

public class A04 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String texto = "El sol brillaba sobre un valle tranquilo donde árboles antiguos " +
                "susurraban historias olvidadas. " +
                "Caminaba despacio una figura solitaria, " +
                "llevando consigo un cuaderno lleno de recuerdos," +
                " frases, promesas y canciones nunca cantadas. " +
                "Pájaros cruzaban el aire mientras el viento agitaba hojas secas. " +
                "A lo lejos, un río serpenteaba entre piedras lisas, reflejando destellos dorados." +
                " Cada paso resonaba como eco en la memoria." +
                " El tiempo parecía detenerse, invitando a soñar. Allí nacían preguntas," +
                " silencios, risas, lágrimas, miedos, esperanzas, caminos, destinos, señales," +
                " búsquedas, secretos, encuentros, despedidas, instantes, oportunidades, palabras," +
                " gestos, abrazos, miradas, viajes, aventuras, juegos, descubrimientos," +
                " decisiones, amaneceres, noches, luces, sombras, mares," +
                " montañas, ciudades, desiertos, huellas, raíces, estrellas.";

        String rutaFichero = "src/Archivos/A04";

        String buscar;

        System.out.println("Introduce una palabra y te diré cuántas veces aparece en el archivo: ");
        buscar = sc.nextLine();

        int resultado;
        try {
            resultado = crearFichero(rutaFichero, texto, buscar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (resultado > 0) {
            System.out.println("La palabra ha aparecido: " + resultado + " veces en el texto.");
        }else{
            System.out.println("La palabra no se encuentra en el archivo.");
        }

    }

    public static int crearFichero(String nombreFichero, String contenido, String palabra) throws IOException {

        File archivo = new File(nombreFichero);
        FileWriter fichero = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fichero);
        bw.write(contenido);
        bw.close();

        //buscamos la palabra en el fichero
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea = br.readLine();
        String[] palabras = linea.split(" ");
        int contador = 0;
        for (int i = 0; i < palabras.length; i++) {
            if (palabras[i].equalsIgnoreCase(palabra)) {
                contador++;
            }
        }
        if (contador > 0){
            return contador;
        }else{
            return -1;
        }

    }

}
