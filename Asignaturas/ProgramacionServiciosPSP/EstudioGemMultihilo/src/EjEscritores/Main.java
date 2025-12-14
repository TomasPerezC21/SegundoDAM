package EjEscritores;

public class Main {
    public static void main(String[] args) {

        Gestor gestor = new Gestor();

        // Creamos los objetos Runnable
        Escritor escritor1 = new Escritor(gestor, "Pepe");
        Lector lector1 = new Lector(gestor);

        // Se los pasamos al constructor de Thread
        Thread t1 = new Thread(escritor1);
        Thread t2 = new Thread(lector1);
        Thread t3 = new Thread(new Lector(gestor)); // Lector anónimo

        t1.start();
        t2.start();
        t3.start();
    }
}