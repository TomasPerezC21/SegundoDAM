import java.util.Scanner;

public class PedirNumeros {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Cuantos números quieres añadir?");
        int cant =  sc.nextInt();


        int[] nums = new int[cant];

        for (int i = 0; i < nums.length; i++) {
            System.out.println("Número " + i + " :" );
            nums[i] = sc.nextInt();
        }

        String[] numerosLetras = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numerosLetras[i] = String.valueOf(nums[i]);
        }

        String ruta = "C:\\Users\\alumno\\Documents\\SegundoDAMTomasPerez\\Asignaturas\\ProgramacionServiciosPSP\\EjercicioNumeros\\artifacts\\Main.jar";

        ProcessBuilder pb = new ProcessBuilder("java", "-jar" ,ruta);

        for (String s : numerosLetras) {
            pb.command().add(s);
        }

        pb.inheritIO();

        try {
            pb.start();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


}
