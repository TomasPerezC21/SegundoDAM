package Ej2CarteroVecino;

import java.util.ArrayList;

public class Vecino implements Runnable{

    private String nombre;
    private Buzon buzon;
    private ArrayList<String> cartas;

    public Vecino(String nombre, Buzon buzon, ArrayList<String> cartas) {
        this.nombre = nombre;
        this.buzon = buzon;
        this.cartas = cartas;
    }


    @Override
    public void run() {

        for (int i = 0; i < this.cartas.size(); i++) {
            try {
                buzon.recoger();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
