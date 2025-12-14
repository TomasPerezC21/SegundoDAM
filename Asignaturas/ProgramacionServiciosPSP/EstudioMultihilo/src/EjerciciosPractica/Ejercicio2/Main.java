package EjerciciosPractica.Ejercicio2;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[] args) {


//        Reloj reloj = new Reloj();
//
//        new Thread(reloj).start();

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        Runnable tareaReloj = new Runnable() {
            @Override
            public void run() {

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

                LocalTime horaMadrid = LocalTime.now();
                LocalTime horaParis = LocalTime.now(ZoneId.of("America/New_York"));

                System.out.println("Hora Madrid: " + horaMadrid.format(dtf) + " - Hora New York: " + horaParis.format(dtf));
            }
        };

        System.out.println("La hora se mostrará a los 3 segundos.");

        executor.scheduleAtFixedRate(tareaReloj, 3, 5, TimeUnit.SECONDS);

        executor.schedule(() ->{
            System.out.println("Ya han pasado los 22 segundos.");
            executor.shutdown();
        },22, TimeUnit.SECONDS);



    }


}
