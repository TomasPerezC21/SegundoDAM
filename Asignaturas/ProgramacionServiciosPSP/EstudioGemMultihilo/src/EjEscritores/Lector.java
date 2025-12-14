package EjEscritores;

class Lector implements Runnable {
    private Gestor gestor;

    public Lector(Gestor gestor) {
        this.gestor = gestor;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                try {
                    gestor.leer();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (InterruptedException e) {}
    }
}