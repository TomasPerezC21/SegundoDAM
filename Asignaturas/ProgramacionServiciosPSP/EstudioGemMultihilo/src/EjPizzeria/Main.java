package EjPizzeria;

public class Main {


    public static void main(String[] args) {

        Mostrador mostrador = new Mostrador();

        Thread cocinero = new Thread(new Cocinero(mostrador));
        Thread repartidor =  new Thread(new Repartidor(mostrador));

        cocinero.start();
        repartidor.start();


    }

}
