package Ejercicio1;

public class MiniCalc {

    public static void main(String[] args) {
        
        int numero1 = Integer.parseInt(args[0]);
        int numero2 = Integer.parseInt(args[1]);
        char operador = args[2].charAt(0);
        
        int resultado = 0;

        switch (operador) {
            case '+':
                resultado = numero1 + numero2;
                break;
            case '-':
                resultado = numero1 - numero2;
                break;
            case '*':
                resultado = numero1 * numero2;
                break;
            case '/':
                resultado = numero1 / numero2;
                break;
        }

        System.out.println("Resultado de minicalc: " + resultado);
        
        
    }
    
    
}
