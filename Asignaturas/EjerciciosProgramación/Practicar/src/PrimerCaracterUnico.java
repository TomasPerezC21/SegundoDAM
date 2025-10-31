import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PrimerCaracterUnico {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce palabra: ");
        String palabra = sc.nextLine();

        Character letra;
        letra = primerCaracter(palabra);

        if (letra != null) {
            System.out.println("Primera letra: " + letra);
        }
        else{
            System.out.println("No hay ninguna letra que solo aparezca una vez.");
        }
        primerCaracter(palabra);
    }

    public static Character primerCaracter(String palabra) {

        boolean letraValida = false;
        char devolver = 0;
        Map<Character, Integer> caracteresPalabra = new HashMap<>();

        for (char letra : palabra.toCharArray()) {
            Integer conteo = caracteresPalabra.getOrDefault(letra, 0);
            caracteresPalabra.put(letra, conteo + 1);
        }

//        for (Map.Entry<Character, Integer> entry : caracteresPalabra.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }

        for (int i = 0; i < palabra.length(); i++) {
            if (caracteresPalabra.containsKey(palabra.charAt(i)) && caracteresPalabra.get(palabra.charAt(i)) == 1) {
                letraValida = true;
                devolver = palabra.charAt(i);
                break;
            }
        }
        if (letraValida) {
            return devolver;
        }
        else{
            return null;
        }
    }

}
