import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        ArrayList<Integer> lista = new ArrayList<>();
        if (args.length > 0) {
            for (int i = 1; i < args.length; i++) {
                lista.add(Integer.parseInt(args[i]));
            }
            Collections.sort(lista);
            System.out.println("El mayor es: " +  lista.getLast());
        }else{
            System.out.println("No has añadido nada.");
        }

    }
}
