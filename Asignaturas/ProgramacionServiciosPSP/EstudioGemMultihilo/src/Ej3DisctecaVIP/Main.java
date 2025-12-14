package Ej3DisctecaVIP;

public class Main {

    public static void main(String[] args) {

        // Creamos la discoteca (El semáforo se crea dentro)
        Discoteca discoteca = new Discoteca();

        System.out.println("--- APERTURA DE PUERTAS (Aforo: 3) ---");

        // Lanzamos 10 personas
        for (int i = 1; i <= 10; i++) {
            Persona p = new Persona("Persona-" + i, discoteca);
            new Thread(p).start();

            // Pequeña pausa para que lleguen escalonados
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
