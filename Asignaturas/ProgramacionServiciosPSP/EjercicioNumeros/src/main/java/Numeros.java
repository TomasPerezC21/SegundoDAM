import java.util.Scanner;

public class Numeros {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce 10 números: ");
        int[] numeros = new int[10];

        for (int i = 0; i < numeros.length; i++) {
            System.out.println(i+1 +": ");
            numeros[i] = sc.nextInt();
        }

        int mayor  = numeros[0];

        for (int i = 1; i < numeros.length; i++) {
            if (numeros[i] > mayor) {
                mayor = numeros[i];
            }
        }

    }

}
