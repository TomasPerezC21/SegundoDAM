package logica;

public class PorductoNoInventarioException extends RuntimeException {

    /*
    public PorductoNoInventarioException() {

        System.out.println("El producto no tiene artículos disponibles.");
    }*/

    public PorductoNoInventarioException(String message) {

        super(message);
    }

}
