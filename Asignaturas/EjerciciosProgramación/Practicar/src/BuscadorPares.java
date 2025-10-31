import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BuscadorPares {


    public static void main(String[] args) {

        Random r = new Random();
        ArrayList<Integer> numeros = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            numeros.add(r.nextInt(20));
        }

        System.out.println("Numeros para hacer debugging");
        for (Integer numero : numeros) {
            System.out.print(numero + ",");
        }

        System.out.println();

        int objetivo = 25;

        ArrayList<int[]> resultado;

        resultado = buscarPares(numeros, objetivo);

        if (resultado.isEmpty()) {
            System.out.println("No hay dos números que sumen " + objetivo);

        }else {
            System.out.println("Resultado: ");
            for (int i = 0; i < resultado.size(); i++) {
                System.out.println(Arrays.toString(resultado.get(i)));
            }
        }
    }

    public static ArrayList<int[]> buscarPares(ArrayList<Integer> numeros, int objetivo) {

        ArrayList<int[]> resultado = new ArrayList<>();

        for (int i = 0; i < numeros.size(); i++) {

            for (int j = i + 1; j < numeros.size(); j++) {

                if (numeros.get(i) + numeros.get(j) == objetivo) {
                    int[] array =  {numeros.get(i), numeros.get(j)};
                    resultado.add(array);
                    break;
                }
            }
        }

        return resultado;


    }



}
