import java.util.Arrays;
import java.util.Scanner;

public class ValidadorAnagramas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce la primera palabra: ");
        String palabra = sc.nextLine().toLowerCase().replaceAll("\\s+","");

        System.out.println("Introduce la segunda palabra: ");
        String palabra2 = sc.nextLine().toLowerCase().replaceAll("\\s+","");

        boolean resultado = esAnagrama(palabra, palabra2);

        if (resultado) {
            System.out.println("Es anagrama");
        }
        else {
            System.out.println("No es anagrama");
        }
    }

    public static boolean esAnagrama(String palabra1,  String palabra2) {

        if (palabra1.length() != palabra2.length()) {
            return false;
        }

        char[] palabra1Alreves = palabra1.toCharArray();
        char[] palabra2Alreves = palabra2.toCharArray();

        Arrays.sort(palabra1Alreves);
        Arrays.sort(palabra2Alreves);

       return Arrays.equals(palabra1Alreves, palabra2Alreves);

    }

}
