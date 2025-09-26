import java.io.IOException;

public class guapo {

    public static void main(String[] args) {
        //prueba01(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println();
        llamadaBlocNotas();
    }

    private static void prueba01(int num1, int num2){
        System.out.println("El resultado de la suma es: " + (num1 + num2));
    }

    private static void llamadaBlocNotas(){
        ProcessBuilder pb =  new ProcessBuilder("cmd.exe", "/c", "start", "ping", "-t", "localhost");

        try{
            Process p = pb.start();
            Thread.sleep(5000);
            System.out.println(p.isAlive());
        }catch(IOException | InterruptedException e){
            throw new RuntimeException(e);

        }
    }


}
