package EjEscritores;

public class Escritor implements Runnable {
    private Gestor gestor;
    private String nombre;

    public Escritor(Gestor gestor, String nombre) {
        this.gestor = gestor;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                try {
                    gestor.escribir("Dato de " + nombre);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (InterruptedException e) {}
    }

}
