package Ejercicio1;

/*
L01	Clasificador de Colecciones	Crea un método que reciba tres parámetros:
        1. Una List<String> de palabras. 2. Un entero longitudMinima.
3. Un booleano iniciarConVocal. El método debe devolver una nueva List<String> que contenga solo las palabras que:
A) Tengan una longitud mayor o igual a longitudMinima.
B)Si iniciarConVocal es true, la palabra debe empezar con vocal (A, E, I, O, U). Si es false, debe empezar con consonante.*/



import java.util.ArrayList;


public class Ejercicio1 {

    public static void main(String[] args) {
        ArrayList<String> listaPalabras = new ArrayList<>();
        listaPalabras.add("Tomás");
        listaPalabras.add("Electrodoméstico");
        listaPalabras.add("Abecedario");
        listaPalabras.add("Programación");
        listaPalabras.add("Sistemas");
        listaPalabras.add("Ecuatoriano");

        ArrayList<String> listaPalabrasMinus = new ArrayList<>();
        for (int i = 0; i < listaPalabras.size(); i++) {
            listaPalabrasMinus.add(listaPalabras.get(i).toLowerCase());
        }

        int longMinima = 5;

        boolean vocal = false;

        ArrayList<String> resultado = clasificadorColecciones(listaPalabrasMinus, longMinima, vocal);

        for (String palabra : resultado) {
            System.out.println(palabra);
        }
    }

    public static ArrayList<String> clasificadorColecciones(ArrayList<String> palabras, int longitudMinima, boolean iniciarConVocal) {

        String[]vocales = {"a","e","i","o","u"};

        ArrayList<String> palabrasVocal = new ArrayList<>();
        ArrayList<String> palabrasConsonante = new ArrayList<>();


        for (String palabra : palabras) {

            if (iniciarConVocal) {
                if (palabra.length() > longitudMinima) {
                    for (String vocal : vocales) {
                        if (palabra.startsWith(vocal)) {
                            palabrasVocal.add(palabra);

                        }
                    }
                }
            }
            if (!iniciarConVocal) {
                if (palabra.length() > longitudMinima) {
                    palabrasConsonante.add(palabra);
                }
            }
        }

        if (iniciarConVocal) {
            return palabrasVocal;
        }
        else{
            return palabrasConsonante;
        }

    }


}
