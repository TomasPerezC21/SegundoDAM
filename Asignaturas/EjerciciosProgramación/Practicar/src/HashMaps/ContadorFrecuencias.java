package HashMaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ContadorFrecuencias {

    public static void main(String[] args) {

        ArrayList<String> palabras = new ArrayList<>();
        palabras.add("HOLA");
        palabras.add("Romero");
        palabras.add("Hola");
        palabras.add("ROMERO");
        palabras.add("hola");
        palabras.add("Tomas");
        palabras.add("Álvaro");
        palabras.add("Tomas");
        palabras.add("Casa");

        ArrayList<String> palabrasMinus = new ArrayList<>();

        for (String palabra : palabras) {
            palabrasMinus.add(palabra.toLowerCase());
        }

        Map<String, Integer> resultado = contadorFrec(palabrasMinus);

        for (Map.Entry<String, Integer> entry : resultado.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

    }


//    public static Map<String, Integer> contadorFrec(ArrayList<String> palabras) {
//
//        Map<String, Integer> resultado = new HashMap<>();
//
//        for (String palabra : palabras) {
//
//            if (!resultado.containsKey(palabra)) {
//                resultado.put(palabra, 1);
//            }
//            else{
//                int contador = resultado.get(palabra);
//
//                resultado.put(palabra, contador+1);
//            }
//        }
//        return resultado;
//    }

public static Map<String, Integer> contadorFrec(ArrayList<String> palabras) {

    Map<String, Integer> resultado = new HashMap<>();

    for (String palabra : palabras) {
        // 1. Obtiene el valor actual (o 0 si no existe)
        int conteoActual = resultado.getOrDefault(palabra, 0);

        // 2. Guarda el nuevo valor (conteo + 1)
        resultado.put(palabra, conteoActual + 1);
    }

    return resultado;
}


}
