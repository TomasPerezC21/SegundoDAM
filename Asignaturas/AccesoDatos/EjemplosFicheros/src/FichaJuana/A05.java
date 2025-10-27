package FichaJuana;

import java.io.*;
import java.util.HashMap;

public class A05 {

    public static void main(String[] args) {

        String nombreFichero = "src/Archivos/A05";

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
                " montañas, ciudades, desiertos, huellas, raíces, estrellas. java";

        try {
            crearFichero(texto, nombreFichero);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            revisarTexto(nombreFichero);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void crearFichero(String texto, String nombreFichero) throws IOException {

        File archivo = new File(nombreFichero);
        FileWriter fw = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(texto);
        bw.close();

    }

    public static void revisarTexto(String fichero) throws IOException {

        FileReader fr = new FileReader(fichero);
        BufferedReader br = new BufferedReader(fr);

        int caracteresTexto = 0;
        int contadorLineas = 0;

        int contadorA = 0;
        int contadorE = 0;
        int contadorI = 0;
        int contadorO = 0;
        int contadorU = 0;

        Boolean java = false;

        String linea = br.readLine();

        while (linea != null) {
            linea = linea.toUpperCase();
            char[] caracteres = linea.toCharArray();
            for (int i = 0; i < caracteres.length; i++) {
                if (caracteres[i] == 'A' ) {
                    contadorA++;
                }
                if (caracteres[i] == 'E' ) {
                    contadorE++;
                }
                if (caracteres[i] == 'I' ) {
                    contadorI++;
                }
                if (caracteres[i] == 'O' ) {
                    contadorO++;
                }
                if (caracteres[i] == 'U' ) {
                    contadorU++;
                }
            }
            caracteresTexto += caracteres.length;
            contadorLineas++;
            String[] palabrasLinea = linea.split(" ");
            for (int i = 0; i < palabrasLinea.length; i++) {
                if (palabrasLinea[i].equalsIgnoreCase("Java")) {
                    java = true;
                    break;
                }
            }
            linea = br.readLine();
        }

        HashMap<Character, Integer> recuentoVocales =  new HashMap<>();
        recuentoVocales.put('A', contadorA);
        recuentoVocales.put('E', contadorE);
        recuentoVocales.put('I', contadorI);
        recuentoVocales.put('O', contadorO);
        recuentoVocales.put('U', contadorU);

        char vocalMaxima = ' ';
        int maxVocal = 0;

        for (HashMap.Entry<Character, Integer> vocal : recuentoVocales.entrySet()) {
            if (vocal.getValue() > maxVocal) {
                maxVocal = vocal.getValue();
                vocalMaxima = vocal.getKey();
            }
        }

        System.out.println("Número de caracteres en el texto: " + caracteresTexto);
        System.out.println("Número líneas con info: "  + contadorLineas);
        System.out.println("Vocal que más aparece en el fichero: " + vocalMaxima + " aparece " + maxVocal);

        if (java) {
            System.out.println("La palabra java esta presente en el fichero.");
        }
        else{
            System.out.println("La palabra java no aparece en el fichero.");
        }
    }

}
