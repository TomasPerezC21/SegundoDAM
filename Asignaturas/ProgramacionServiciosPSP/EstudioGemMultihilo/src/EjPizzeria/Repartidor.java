package EjPizzeria;

public class Repartidor implements Runnable{

    private Mostrador mostrador;

    public  Repartidor(Mostrador mostrador) {
        this.mostrador = mostrador;
    }

    @Override
    public void run() {

        for (int i = 0; i <10 ; i++) {

            mostrador.servir();

        }

    }

}
