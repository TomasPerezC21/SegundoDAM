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

        boolean vocal = true;

        ArrayList<String> resultado;

        resultado = clasificadorColecciones(listaPalabrasMinus, longMinima, vocal);

        for (String palabra : resultado) {
            System.out.println(palabra);
        }
    }

    public static ArrayList<String> clasificadorColecciones(ArrayList<String> palabras, int longitudMinima, boolean iniciarConVocal) {

        ArrayList<String> resultado = new ArrayList<>();

        String vocales = "aeiou";

        for (String palabra : palabras) {

            //si el tamaño es menor pasamos a la siguiente palabra
            if (palabra.length() < longitudMinima) {
                continue;
            }

            char primeraLetra = palabra.charAt(0);
            //devuelve -1 si la primera letra no se encuentra en la string vocales
            boolean esVocal = (vocales.indexOf(primeraLetra) != -1);

            if (iniciarConVocal == esVocal) {
                resultado.add(palabra);
            }
        }
        return resultado;
    }
}

