package Ej2Hospital;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int numQuirofanos = obtenerQuirofanosUsuario(sc);
        int numEquiposMedicos = obtenerEquiposMedicosUsuario(sc);

        Object[] quirofanos = new Object[numQuirofanos];
        Object[] equiposMedicos = new Object[numEquiposMedicos];
        
        for (int i = 0; i < numQuirofanos; i++) {
            quirofanos[i] = new Object();
        }
        
        for (int i = 0; i < numEquiposMedicos; i++) {
            equiposMedicos[i] = new Object();
        }

        for (int i = 0; i < 10; i++) {
            Object quirofano = quirofanos[i];
            Object equiMedico = equiposMedicos[(i+1)%equiposMedicos.length];

            Cirujia c = new Cirujia(i, quirofano, equiMedico);
            new Thread(c).start();
        }



    }

    private static int obtenerQuirofanosUsuario(Scanner sc){
        int num;
        do {
            System.out.println("Introduce el número de quirófanos (2 a 4): ");
            num = sc.nextInt();
        }while(num < 2 || num > 4);
        return num;
    }

    private static int obtenerEquiposMedicosUsuario(Scanner sc){
        int num;
        do {
            System.out.println("Introduce el número de equipos médicos (2 a 4): ");
            num = sc.nextInt();
        }while(num < 2 || num > 4);
        return num;
    }

}
