package EjPizzeria;

public class Cocinero implements Runnable {

    private Mostrador mostrador;

    public Cocinero(Mostrador mostrador) {
        this.mostrador = mostrador;
    }


    @Override
    public void run() {

        for (int i = 0; i <10 ; i++) {


            System.out.println("Cocinando pipsa...");

            try {
                Thread.sleep(3000L);
                mostrador.cocinar();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
