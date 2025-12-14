package Ej1PuenteSoloCarril;

public class Main {

    public static void main(String[] args) {

        Puente puente = new Puente();

        Thread c1 = new Thread(new Coche("Seat", puente));
        Thread c2 = new Thread(new Coche("Scirocco", puente));

        c1.start();
        c2.start();

    }


}
