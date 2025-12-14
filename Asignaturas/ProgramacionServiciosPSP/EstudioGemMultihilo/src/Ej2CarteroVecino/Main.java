package Ej2CarteroVecino;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> cartas = new ArrayList<>();
        cartas.add("Buenas noches");
        cartas.add("Buenas tardes");
        cartas.add("Quiero pan con atun");
        cartas.add("Quiero pan con queso");
        cartas.add("Quiero pan con mermelada");

        Buzon buzon = new Buzon();

        Thread cartero = new Thread(new Cartero("Borja", buzon,  cartas));
        Thread vecino = new Thread(new Vecino("Tomas", buzon, cartas));

        cartero.start();
        vecino.start();


    }

}
