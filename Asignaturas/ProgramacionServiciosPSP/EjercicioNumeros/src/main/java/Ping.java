import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Ping {

    public static void main(String[] args) {

        ProcessBuilder pb = new ProcessBuilder("ping", "localhost"); //añadir -t para que se ejecute mas tiempo

        pb.inheritIO();

        try{
           Process p  = pb.start();

           boolean cierre  = p.waitFor(15, TimeUnit.SECONDS);

           if (cierre){
               System.out.println("Proceso cerrado antes de los 15 segundos");
           }else {
               System.out.println("Se ha cerrado a los 15 segundos");
               p.destroyForcibly();
               p.waitFor();

           }
            System.out.println("Esta vivo: " +p.isAlive());
            System.out.println("Codigo de finalización:" + p.exitValue());

        }catch(IOException | InterruptedException e){
           throw new RuntimeException(e);
        }

    }


}
