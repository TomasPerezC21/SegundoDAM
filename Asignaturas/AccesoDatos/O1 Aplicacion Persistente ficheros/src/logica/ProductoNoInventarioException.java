package logica;

public class ProductoNoInventarioException extends RuntimeException {

    /*
    public PorductoNoInventarioException() {

        System.out.println("El producto no tiene artículos disponibles.");
    }*/

    public ProductoNoInventarioException(String message) {
        super(message);
    }

}
