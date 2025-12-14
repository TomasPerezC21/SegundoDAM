package EjemploCallableSencillo;
import java.util.concurrent.*;


public class EjemploCallable {
    public static void main(String[] args) throws Exception {

        ExecutorService pool = Executors.newFixedThreadPool(1);

        // Definimos la tarea con CALLABLE (Lambda que retorna algo)
        Callable<Integer> tareaCalculo = () -> {
            Thread.sleep(1000);
            return 5 + 5; // Retornamos el 10
        };

        // Enviamos y recibimos un "Vale por un resultado futuro"
        Future<Integer> futuro = pool.submit(tareaCalculo);

        System.out.println("Esperando resultado...");

        // .get() BLOQUEA hasta que el hilo termine y tenga el dato
        Integer resultado = futuro.get();

        System.out.println("Resultado recibido: " + resultado);

        pool.shutdown();
    }
}