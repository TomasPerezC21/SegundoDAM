package Ej1FabricaVehiculos;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random aleatorio = new Random();

        Concesionario concesionario = new Concesionario();

        TallerCoches tallerCoches = new TallerCoches(concesionario);
        TallerMotos tallerMotos = new TallerMotos(concesionario);

        Thread tallerCochesThread = new Thread(tallerCoches);
        Thread tallerMotosThread = new Thread(tallerMotos);

        tallerCochesThread.start();
        tallerMotosThread.start();

        try {
            Thread.sleep(obtenerTiempoEspera(aleatorio));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 10; i++) {
            Thread ClienteCoche = new Thread(new ClienteCoches((i+1),concesionario));
            ClienteCoche.start();
            try {
                Thread.sleep(obtenerTiempoEspera(aleatorio));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        try {
            Thread.sleep(obtenerTiempoEspera(aleatorio));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 10; i++) {
            Thread ClienteMoto = new Thread(new ClienteMotos((i+1),concesionario));
            ClienteMoto.start();
            try {
                Thread.sleep(obtenerTiempoEspera(aleatorio));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }

    private static int obtenerTiempoEspera(Random random){

        return random.nextInt(2000, 4000);
    }






}
