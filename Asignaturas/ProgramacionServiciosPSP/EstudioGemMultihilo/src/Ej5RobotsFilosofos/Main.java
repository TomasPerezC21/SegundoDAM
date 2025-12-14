package Ej5RobotsFilosofos;

public class Main {

    public static void main(String[] args) {

        Object[] soldadores = new Object[5];

        for (int i = 0; i < soldadores.length; i++) {
            soldadores[i] = new Object();
        }

        for (int i = 0; i < 5; i++) {
            Object soldadorIzquierda = soldadores[i];
            Object soldadorDerecha = soldadores[(i+1)%5];

            Robot r = new Robot(i, soldadorIzquierda, soldadorDerecha);
            new Thread(r).start();
        }

    }

}
