package TareaGuillermo2.Ejercicio1;

public class App1 {
    public static void main(String[] args) {

        double num1 = Double.parseDouble(args[0]);
        double num2 = Double.parseDouble(args[1]);

        char operador = String.valueOf(args[2]).charAt(0);

        double resultado = calculadora(num1, num2, operador);

        if (resultado == Double.MIN_VALUE) {
            System.exit(0);
        }
        System.out.println("Resultado: " + resultado);
    }

    public static double calculadora(double num1, double num2,  char operador) {

        if (num1 < num2) {
            num1 = num2;
            num2 = num1;
        }
        switch (operador) {
            case '+':
                return num1 + num2;
                case '-':
                    return num1 - num2;
                    case '*':
                        return num1 * num2;
                        case '/':
                            if (num2 == 0) {
                                System.out.println("No se puede dividir por zero");
                                break;
                            }
                            return num1 / num2;
        }
        return Double.MIN_VALUE;
    }

}
