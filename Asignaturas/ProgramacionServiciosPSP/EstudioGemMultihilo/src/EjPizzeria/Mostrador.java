package EjPizzeria;


public class Mostrador {

    private int cantidad;
    private int maximo = 3;

    public Mostrador() {
        this.cantidad = 0;
    }

    public synchronized void cocinar(){

        while(cantidad == maximo){
            System.out.println("Mostrador lleno. Cocinero espera.");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        cantidad++;
        System.out.println("Pipsa lista para entrega. Hay " + cantidad);

        notifyAll();
    }

    public synchronized void servir(){

        while(cantidad==0){
            System.out.println("No hay pizzas hechas. Repartidor espera");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        cantidad--;
        System.out.println("El repartidor se lleva una pipsa.");

        notifyAll();
    }


}
